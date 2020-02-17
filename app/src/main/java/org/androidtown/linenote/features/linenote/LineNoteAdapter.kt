package org.androidtown.linenote.features.linenote

import android.content.Context
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
@Inject constructor() : RecyclerView.Adapter<LineNoteAdapter.ViewHolder>(){

    @Inject lateinit var context: Context

    internal var collection : List<LineNoteView> by Delegates.observable(emptyList()){
        _,_,_ -> notifyDataSetChanged()
    }

    internal var clickListener : (Int)->Unit = {_ -> }

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position],context,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.linenote_recyclerview_note_item))

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(lineNoteView: LineNoteView, context: Context, clickListener: (Int)->Unit ){
            itemView.linenote_textview_title.text = lineNoteView.title
            itemView.linenote_textview_content.text = lineNoteView.content
            //itemView.linenote_textview_test.text = lineNoteView.thumbnail
            Glide.with(context)
                .load(lineNoteView.thumbnail)
                .error(android.R.drawable.ic_delete)
                .into(itemView.linenote_imageview_thumnail)
            itemView.linenote_recyclerview_note_item_body.setOnClickListener{
                clickListener(lineNoteView.id)
            }
        }
    }
}