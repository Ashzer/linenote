package org.androidtown.linenote.features.notepage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.androidtown.linenote.core.platform.BaseViewModel
import org.androidtown.linenote.features.ImageData
import org.androidtown.linenote.features.NoteData
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.usecases.*

import javax.inject.Inject
import javax.inject.Singleton

class NotePageViewModel
@Inject constructor(
    private val getImages: GetImages,
    private val getNoteById: GetNoteById,
    private val updateNote: UpdateNote,
    private val insertImage: InsertImage,
    private val deleteImage: DeleteImage,
    private val insertNote: InsertNote,
    private val deleteNoteById: DeleteNoteById,
    private val deleteImagesById: DeleteImagesById
) : BaseViewModel() {
    var imageList: MutableLiveData<List<NotePageImageView>> = MutableLiveData()
    var note: MutableLiveData<NotePageView> = MutableLiveData()
    private var deletedImages: MutableList<ImageData> = mutableListOf()
    var emptyImage = ImageData.empty()
    var isNoImage = true
    var thisNoteId : Int = 0
    private fun newNote(noteData: NoteData) =
        insertNote(noteData) { it.fold(::handleFailure, ::handleNewNote) }
    fun loadNote(noteId: Int) = getNoteById(noteId) { it.fold(::handleFailure, ::handleNoteList) }
    fun deleteNote(noteId:Int) = deleteNoteById(noteId)
    private fun loadImages(noteId: Int) =
        getImages(noteId) { it.fold(::handleFailure, ::handleNotePageImageList) }

    private fun saveNote(noteData: NoteData) = updateNote(noteData)
    private fun saveImage(imageData: ImageData) = insertImage(imageData)
    private fun deleteThisImage(imageData: ImageData) = deleteImage(imageData)
    private fun deleteImages(noteId:Int) = deleteImagesById(noteId)

    fun deleteNote(){
        deleteNote(thisNoteId)
        deleteImages(thisNoteId)
    }
    fun initializeNote(noteId : Int){
        when(noteId){
            -1 ->{
                newNote(NoteData(0,"",""))
            }
            else->{
                thisNoteId =noteId
            }
        }.also {
            loadNote(thisNoteId)
            loadImages(thisNoteId)
        }

    }

    private fun handleNewNote(noteId: Long) {
        thisNoteId=noteId.toInt()
        note.value = NotePageView(thisNoteId, "", "")
     //   saveImage(ImageData(0,thisNoteId,"NoNoteImage"))

        //imageList.value = listOf(NotePageImageView(0, thisNoteId, "NoNoteImage"))
        // Log.d("returnId","note id : ${noteId.toInt()}")
    }


    fun saveNotePage(noteData: NoteData) {
        var noteId: Int
        when (noteData.id) {
            -1 -> noteId = note.value!!.id
            else -> noteId = noteData.id
        }

        saveNote(NoteData(noteId, noteData.title, noteData.content))
        if (deletedImages.isNotEmpty()) {
            deletedImages.forEach { imageData ->
                deleteThisImage(imageData)
                Log.d("imageflow", "deleted ${imageData.image}")
            }
        }

        if (isNoImage) {
            deleteThisImage(emptyImage)
        }
        imageList.value!!.forEach { notePageImageView ->
            saveImage(
                ImageData(
                    notePageImageView.id,
                    noteId,
                    notePageImageView.image
                )
            )
        }

        if (imageList.value!!.isEmpty()) {
            saveImage(
                ImageData(
                    0,
                    noteId,
                    "NoNoteImage"
                )
            )
        }
    }

    fun addImage(imageData: ImageData) {
        //this.imageList.value = this.imageList.value!!.toMutableList().add(NotePageImageView(imageData.id,imageData.noteId,imageData.image))

        val temp = this.imageList.value!!.toMutableList()
        temp.add(NotePageImageView(imageData.id, thisNoteId, imageData.image))

        this.imageList.postValue(temp.toList())
        temp.clear()

    }

    fun removeImage(imageData: ImageData) {
        val temp = this.imageList.value!!.toMutableList()
        temp.remove(NotePageImageView(imageData.id, imageData.noteId, imageData.image))
        this.imageList.postValue(temp.toList())
        deletedImages.add(imageData)
        temp.clear()
    }

    fun removeLastImage(){
        val temp = this.imageList.value!!.toMutableList()
        temp.removeAt(temp.lastIndex)
        this.imageList.postValue(temp.toList())
        temp.clear()
    }

    private fun handleNoteList(noteData: NoteData) {
        note.value = noteData.let {
            NotePageView(
                it.id,
                it.title,
                it.content
            )
        }
    }

    private fun handleNotePageImageList(imageData: List<ImageData>) {


        if(imageData.isNullOrEmpty()) {
            this.imageList.value= listOf()
        }else{
            if (imageData[0].image == "NoNoteImage" && imageData.size == 1) {
                this.imageList.value = listOf()
                emptyImage = imageData[0]
                isNoImage = true
            } else if (imageData[0].image == "NoNoteImage" && imageData.size > 1) {
                this.imageList.value = imageData.subList(1, imageData.size - 1).map {
                    NotePageImageView(it.id, it.noteId, it.image)
                }
                isNoImage = false
            } else {
                this.imageList.value = imageData.map {
                    NotePageImageView(it.id, it.noteId, it.image)
                }
                isNoImage = false
            }
        }
    }

}
