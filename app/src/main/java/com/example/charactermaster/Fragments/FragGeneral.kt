package com.example.charactermaster.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.charactermaster.Character
import com.example.charactermaster.R
import kotlinx.android.synthetic.main.fragment_general.view.*


private const val CHARACTER = "character"

class FragGeneral : Fragment() {
    private var character: Character? = null

    private var editName: EditText? = null
    private val editLevel: EditText? = null
    private val editXP: EditText? = null
    private val editRace: EditText? = null
    private val editAlignment: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getParcelable(CHARACTER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater!!.inflate(R.layout.fragment_general, container, false)

        if (character != null) {
            v.editName.setText(character?.charName)
            v.editAlignment.setText(character?.charAlignment)
            v.editLevel.setText(character?.charLevel.toString())
            v.editXP.setText(character?.charXP.toString())
            v.editRace.setText(character?.charRace)
        }

        return v
    }

    companion object {
        fun newInstance(): FragGeneral =
            FragGeneral()
    }
}