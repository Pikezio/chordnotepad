package com.example.chordnotepad.fragments

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chordnotepad.R
import com.example.chordnotepad.data.NoteViewModel
import kotlinx.android.synthetic.main.fragment_view.view.*


class ViewFragment : Fragment() {

    private val args: ViewFragmentArgs by navArgs()
    private lateinit var mNoteViewModel : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.view_title.text = args.currentNote.title
        view.view_content.text = args.currentNote.content

        val sharedPref = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: return view
        val fontSize = sharedPref.getInt("font_size", 30)
        view.view_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
        val darkMode = sharedPref.getBoolean("dark_mode", false)
        if(darkMode){
            view.view_content.setBackgroundColor(Color.BLACK)
            view.setBackgroundColor(Color.BLACK)
            view.view_content.setTextColor(Color.WHITE)
        } else {
            view.view_content.setBackgroundColor(Color.WHITE)
            view.setBackgroundColor(Color.WHITE)
            view.view_content.setTextColor(Color.BLACK)
        }

        view.delete_note_btn.setOnClickListener { deleteNote() }
        return view
    }

    private fun deleteNote(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(), "Removed ${args.currentNote.title}.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_viewFragment_to_notesFragment)
        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.title}?")
        builder.create().show()
    }

}