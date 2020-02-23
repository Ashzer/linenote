package org.androidtown.linenote.features.notepage

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.notepage_fragment.view.*
import kotlinx.android.synthetic.main.notepage_recyclerview_note_item.view.*
import org.androidtown.linenote.R
import org.androidtown.linenote.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class NotePageAdapter
@Inject constructor() : RecyclerView.Adapter<NotePageAdapter.ViewHolder>(){

    @Inject lateinit var context: Context

    internal var collection : List<NotePageImageView> by Delegates.observable(emptyList()){
        _,_,_ -> notifyDataSetChanged()
    }

    internal var longClickListener : (NotePageImageView)->Boolean = {true}

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position],context,longClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.notepage_recyclerview_note_item))

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        fun bind(notePageImageView: NotePageImageView, context: Context, longClickListener:(NotePageImageView) -> Boolean){


            Glide.with(context)
                .load(notePageImageView.image)
                .error(android.R.drawable.ic_delete)
                .into(itemView.notepage_imageview_image)
            //itemView.notepage_textview_test.text =  notePageImageView.image

            itemView.notepage_recyclerview_note_item_body.setOnLongClickListener {
                longClickListener(notePageImageView)
            }

        }
    }
}