package org.androidtown.linenote.features.linenote

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.linenote_recyclerview_note_item.view.*
import org.androidtown.linenote.R
import org.androidtown.linenote.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class LineNoteAdapter
@Inject constructor() : RecyclerView.Adapter<LineNoteAdapter.ViewHolder>() {

    @Inject
    lateinit var context: Context

    internal var noteCollection: List<LineNoteView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var imageCollection: List<LineNoteImageView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (Int) -> Unit = { _ -> }

    override fun getItemCount() = noteCollection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            noteCollection[position],
            imageCollection.getOrElse(position) { LineNoteImageView.empty() },
            context,
            clickListener
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.linenote_recyclerview_note_item))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            lineNoteView: LineNoteView,
            lineNoteImageView: LineNoteImageView,
            context: Context,
            clickListener: (Int) -> Unit
        ) {

            itemView.linenote_textview_title.text = lineNoteView.title
            itemView.linenote_textview_content.text = lineNoteView.content
            Glide.with(context)
                .load(lineNoteImageView.image)
                .error(android.R.drawable.ic_delete)
                .into(itemView.linenote_imageview_thumnail)

            itemView.linenote_recyclerview_note_item_body.setOnClickListener {
                clickListener(lineNoteView.id)
            }
        }
    }
}