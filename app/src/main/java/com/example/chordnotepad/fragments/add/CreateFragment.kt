package com.example.chordnotepad.fragments.add

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chordnotepad.R
import com.example.chordnotepad.data.Note
import com.example.chordnotepad.data.NoteViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_create.view.*


class CreateFragment : Fragment(), View.OnClickListener {
    private lateinit var mNoteViewModel : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.done_btn.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        a_chord_btn.setOnClickListener(this)
        b_chord_btn.setOnClickListener(this)
        c_chord_btn.setOnClickListener(this)
        d_chord_btn.setOnClickListener(this)
        e_chord_btn.setOnClickListener(this)
        f_chord_btn.setOnClickListener(this)
        g_chord_btn.setOnClickListener(this)
        sharp_btn.setOnClickListener(this)
        flat_btn.setOnClickListener(this)
        minor_btn.setOnClickListener(this)
        seven_btn.setOnClickListener(this)
        section_btn.setOnClickListener(this)
        next_bar_btn.setOnClickListener(this)
        del_btn.setOnClickListener(this)
        multiline_text.append("| Intro |\n| ")
    }

    override fun onClick(v: View?) {
        var currentText : String = multiline_text.text.toString()
        when(v!!.id)
        {
            R.id.a_chord_btn -> currentText += addLetter(currentText, "A")
            R.id.b_chord_btn -> currentText += addLetter(currentText, "B")
            R.id.c_chord_btn -> currentText += addLetter(currentText, "C")
            R.id.d_chord_btn -> currentText += addLetter(currentText, "D")
            R.id.e_chord_btn -> currentText += addLetter(currentText, "E")
            R.id.f_chord_btn -> currentText += addLetter(currentText, "F")
            R.id.g_chord_btn -> currentText += addLetter(currentText, "G")
            R.id.sharp_btn -> currentText += "#"
            R.id.flat_btn -> currentText += "b"
            R.id.minor_btn -> currentText += "m"
            R.id.seven_btn -> currentText += "7"
            R.id.next_bar_btn -> currentText += " | "
            R.id.section_btn -> currentText += addSection(currentText)
            R.id.del_btn -> currentText = currentText.dropLast(1)
        }
        multiline_text.setText(currentText)
        multiline_text.setSelection(currentText.length)
    }

    private fun addSection(text: String) : String{
        val section = "new section"
        if (text.takeLast(2) == "| "){
            return "\n| $section |\n|"
        }
        return " |\n| $section |\n|"
    }

    private fun addLetter(text : String, letter : String) : String{
        if(text.last() == ' '){
            return letter
        }else{
            return " $letter"
        }
    }

    private fun closeKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun insertDataToDatabase(){
        val title = editTitle.text.toString()
        val content = multiline_text.text.toString()

        if (inputCheck(title)){
            val newNote = Note(0, title, content)
            mNoteViewModel.addNote(newNote)
            Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createFragment_to_notesFragment)
            closeKeyBoard()
        } else {
            Toast.makeText(requireContext(), "Title is empty!", Toast.LENGTH_LONG).show()
        }
    }

    // Checks if a field is not empty
    private fun inputCheck(title : String) : Boolean{
        return !(TextUtils.isEmpty(title))
    }
}