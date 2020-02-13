package org.androidtown.linenote.features.linenote

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.linenote_fragment.view.*
import kotlinx.android.synthetic.main.linenote_recyclervew_note_item.view.*
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
            = ViewHolder(parent.inflate(R.layout.linenote_recyclervew_note_item))

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(lineNoteView: LineNoteView, context: Context, clickListener: (Int)->Unit ){
            itemView.linenote_textview_title.text = lineNoteView.title
            itemView.linenote_textview_content.text = lineNoteView.content

            itemView.linenote_recyclerview_note_item_body.setOnClickListener{
                clickListener(lineNoteView.id)
            }
        }
    }
}