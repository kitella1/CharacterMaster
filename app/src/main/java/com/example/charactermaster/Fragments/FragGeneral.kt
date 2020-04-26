package com.example.charactermaster.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.charactermaster.Character
import com.example.charactermaster.CharacterDetails
import com.example.charactermaster.R
import kotlinx.android.synthetic.main.fragment_general.*
import kotlinx.android.synthetic.main.fragment_general.view.*
import kotlinx.android.synthetic.main.fragment_general.view.spinClass
import kotlinx.android.synthetic.main.list_view_character.view.*


private const val CHARACTER = "character"

class FragGeneral : Fragment() {
    private var character: Character? = null
    private var viewGroup: ViewGroup? = null
    private var editable: Boolean = true
    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getParcelable(CHARACTER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewGroup = container
        v = inflater.inflate(R.layout.fragment_general, viewGroup, false)

        if (character != null) {
            v.editName.setText(character?.name)
            v.imgProfile.setImageResource(resources.getIdentifier("@drawable/" + character!!.image,
                "drawable",
                context?.packageName
            ))
            v.editAlignment.setText(character?.alignment)
            v.editLevel.setText(character?.level.toString())
            v.editXP.setText(character?.xp.toString())
            v.editRace.setText(character?.race)
        }

        return v
    }


    companion object {
        fun newInstance(): FragGeneral =
            FragGeneral()
    }
}