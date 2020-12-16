package com.example.chordnotepad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chordnotepad.R

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.create_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.notes_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.settings_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.create_btn -> navController.navigate(
                R.id.action_mainFragment_to_createFragment
            )
            R.id.notes_btn -> navController.navigate(
                R.id.action_mainFragment_to_notesFragment
            )
            R.id.settings_btn -> navController.navigate(
                R.id.action_mainFragment_to_settingsFragment
            )
        }
    }
}