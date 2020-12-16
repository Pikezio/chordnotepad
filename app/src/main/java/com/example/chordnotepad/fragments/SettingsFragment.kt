package com.example.chordnotepad.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chordnotepad.R
import kotlinx.android.synthetic.main.fragment_settings.view.*


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val sharedPref = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: return view

        val small_btn = view.small_font_btn
        val regular_btn = view.regular_font_btn
        val big_btn = view.big_font_btn

        var fontSize = sharedPref.getInt("font_size", 30)
        view.dark_mode.isChecked = sharedPref.getBoolean("dark_mode", false)
        view.dark_mode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
            {
                with (sharedPref.edit()) {
                    putBoolean("dark_mode", true)
                    apply()
                }
            }
            else{
                with (sharedPref.edit()) {
                    putBoolean("dark_mode", false)
                    apply()
                }
            }

        }

        view.small_font_btn.setOnClickListener{
            fontSize = 24
            small_btn.setTextColor(Color.BLACK)
            regular_btn.setTextColor(Color.WHITE)
            big_btn.setTextColor(Color.WHITE)
            with (sharedPref.edit()) {
                putInt("font_size", fontSize)
                apply()
            }
        }
        view.regular_font_btn.setOnClickListener{
            fontSize = 30
            small_btn.setTextColor(Color.WHITE)
            regular_btn.setTextColor(Color.BLACK)
            big_btn.setTextColor(Color.WHITE)
            with (sharedPref.edit()) {
                putInt("font_size", fontSize)
                apply()
            }
        }
        view.big_font_btn.setOnClickListener{
            fontSize = 36
            small_btn.setTextColor(Color.WHITE)
            regular_btn.setTextColor(Color.WHITE)
            big_btn.setTextColor(Color.BLACK)
            with (sharedPref.edit()) {
                putInt("font_size", fontSize)
                apply()
            }
        }
        if (fontSize == 24){
            small_btn.callOnClick()
        }
        if (fontSize == 30){
            regular_btn.callOnClick()
        }
        if (fontSize == 36){
            big_btn.callOnClick()
        }
        return view
    }

}