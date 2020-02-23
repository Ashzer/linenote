package org.androidtown.linenote.features.notepage

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.notepage_dialog.view.*
import kotlinx.android.synthetic.main.notepage_fragment.*
import kotlinx.android.synthetic.main.notepage_recyclerview_note_item.*
import org.androidtown.linenote.R
import org.androidtown.linenote.core.extension.*
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseFragment
import org.androidtown.linenote.features.ImageData
import org.androidtown.linenote.features.NoteData
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NotePageFragment(private val intent: Intent) : BaseFragment() {
    override fun layoutId() = R.layout.notepage_fragment

    private lateinit var notePageViewModel: NotePageViewModel
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var notePageAdapter: NotePageAdapter

    private val REQUEST_TAKE_PHOTO = 1
    private val PICK_FROM_ALBUM = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        notePageViewModel = viewModel(viewModelFactory) {
            observe(imageList, ::renderImage)
            observe(note, ::renderNote)
        }

        var noteId: Int = intent.getIntExtra("id", 1)
        notePageViewModel.initializeNote(noteId)



    }

    override fun onBackPressed() {
        super.onBackPressed()
        when(intent.getIntExtra("id", 1)) {
            -1 -> notePageViewModel.deleteNote(notePageViewModel.thisNoteId)
        }
    }

    override fun onResume() {
        super.onResume()
        initializeView()
    }

    private fun renderImage(notePageViews: List<NotePageImageView>?) {
        notePageAdapter.collection = notePageViews.orEmpty()
    }

    private fun renderNote(note: NotePageView?) {
        notepage_textview_title.setText(note?.title)
        notepage_textview_content.setText(note?.content)
    }

    private fun initializeView() {
        notepage_recyclerview_image.layoutManager = GridLayoutManager(activity, 5)
        notepage_recyclerview_image.adapter = notePageAdapter

        var noteId: Int = intent.getIntExtra("id", -1)
            notepage_button_delete.invisible()
        if (noteId == -1) {
        } else {
            notePageViewModel.loadNote(noteId)
            notepage_textview_title.unfocusble()
            notepage_textview_content.unfocusble()
            notepage_textview_title.setBackgroundColor(Color.RED)
            notepage_textview_content.setBackgroundColor(Color.RED)
            notepage_linearlayout_buttonbox.invisible()
            notepage_textview_message.invisible()
            notepage_button_save.invisible()
            notepage_button_delete.visible()
        }

        notepage_button_edit.setOnClickListener {
            when(notepage_linearlayout_buttonbox.visibility){
                View.VISIBLE -> {
                    notepage_linearlayout_buttonbox.invisible()
                    notepage_textview_message.invisible()
                    notepage_button_save.invisible()
                    notepage_button_delete.visible()
                    notepage_textview_title.unfocusble()
                    notepage_textview_content.unfocusble()
                    notepage_textview_title.setBackgroundColor(Color.RED)
                    notepage_textview_content.setBackgroundColor(Color.RED)
                }
                View.GONE -> {
                    notepage_linearlayout_buttonbox.visible()
                    notepage_textview_message.visible()
                    notepage_button_save.visible()
                    notepage_button_delete.invisible()
                    notepage_textview_title.focusble()
                    notepage_textview_content.focusble()
                    notepage_textview_title.setBackgroundColor(Color.WHITE)
                    notepage_textview_content.setBackgroundColor(Color.WHITE)
                }
            }

        }

        notepage_button_save.setOnClickListener {
            var title = notepage_textview_title.text.toString()
            var content = notepage_textview_content.text.toString()
            notePageViewModel.saveNotePage(
                NoteData(
                    noteId,
                    title,
                    content
                )
            )
            Toast.makeText(activity, "노트가 저장되었습니다.", Toast.LENGTH_SHORT).show()
            navigator.showMain(activity!!)
        }

        notepage_button_delete.setOnClickListener {
            notePageViewModel.deleteNote()
            Toast.makeText(activity, "노트가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            navigator.showMain(activity!!)
        }

        notePageAdapter.clickListener = { notePageImageView ->
            if(notepage_textview_title.isFocusable) {
                    val dialogClickListener = DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            notePageViewModel.removeImage(
                                ImageData(
                                    notePageImageView.id,
                                    notePageImageView.noteId,
                                    notePageImageView.image
                                )
                            )
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                        }
                    }
                }
                AlertDialog.Builder(activity)
                    .setTitle("사진 삭제")
                    .setMessage("해당 사진을 삭제 하시겠습니까?")
                    .setPositiveButton("네", dialogClickListener)
                    .setNegativeButton("아니오", dialogClickListener)
                    .create()
                    .show()
            }
        }

        notepage_button_url.setOnClickListener {
            val inflater = activity!!.layoutInflater.inflate(R.layout.notepage_dialog, null)

            val dialogClickListener = DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        notePageViewModel.addImage(
                            ImageData(
                                0,
                                -1,
                                inflater.notepage_dialog_edittext_url.text.toString()
                            )
                        )
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }

            AlertDialog.Builder(activity)
                .setView(inflater)
                .setTitle("URL 추가")
                .setPositiveButton("추가", dialogClickListener)
                .setNegativeButton("취소", dialogClickListener)
                .create()
                .show()

        }

        notepage_button_camera.setOnClickListener {

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                // Ensure that there's a camera activity to handle the intent
                takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                    // Create the File where the photo should go
                    val photoFile: File? = try {
                        createImageFile()
                    } catch (ex: IOException) {
                        null
                    }
                    // Continue only if the File was successfully created
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            activity!!,
                            "org.androidtown.linenote.features.NotePageActivity",
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                        Log.d("picture file path", photoURI.toString())

                        notePageViewModel.addImage(
                            ImageData(
                                0,
                                -1,
                                photoURI.toString()
                            )
                        )

                    }
                }
            }
        }

        notepage_button_gallery.setOnClickListener {
            Intent(Intent.ACTION_PICK).setType("image/*").also { getGalleryPictureIntent ->
                startActivityForResult(getGalleryPictureIntent, PICK_FROM_ALBUM)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PICK_FROM_ALBUM -> {
                    var uri: Uri = data?.data!!
                    notePageViewModel.addImage(
                        ImageData(
                            0,
                            -1,
                            uri.toString()
                        )
                    )
                }
            }
        }
    }

    private lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
}
