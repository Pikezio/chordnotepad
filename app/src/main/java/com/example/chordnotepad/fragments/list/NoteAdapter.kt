package com.example.chordnotepad.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.chordnotepad.R
import com.example.chordnotepad.data.Note
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>()
{
    private var noteList = emptyList<Note>()

    class NoteViewHolder(v : View) : RecyclerView.ViewHolder(v){
        var title: Button = v.findViewById<Button>(R.id.list_item)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.title.text = currentItem.title
        holder.itemView.list_item.setOnClickListener{
            val action = NotesFragmentDirections.actionNotesFragmentToViewFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false))
    }

    fun setData(note : List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}