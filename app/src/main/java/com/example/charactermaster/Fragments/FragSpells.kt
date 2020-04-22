package com.example.charactermaster.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.charactermaster.R

class FragSpells : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_spells, container, false)
    }

    companion object {
        fun newInstance(): FragSpells =
            FragSpells()
    }
}