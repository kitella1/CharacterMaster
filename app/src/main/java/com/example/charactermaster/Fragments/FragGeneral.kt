package com.example.charactermaster.Fragments

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


private const val CHARACTER = "character"

class FragGeneral : Fragment(), TextWatcher {
    private var character: Character? = null
    private var viewGroup: ViewGroup? = null
    private var editable: Boolean = true
    lateinit var v: View

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
        viewGroup = container
        v = inflater.inflate(R.layout.fragment_general, viewGroup, false)

        /*v.editName.addTextChangedListener(this)
        v.editAlignment.addTextChangedListener(this)
        v.editLevel.addTextChangedListener(this)
        v.editXP.addTextChangedListener(this)
        v.editRace.addTextChangedListener(this)*/

        if (character != null) {
            v.editName.setText(character?.charName)
            v.editAlignment.setText(character?.charAlignment)
            v.editLevel.setText(character?.charLevel.toString())
            v.editXP.setText(character?.charXP.toString())
            v.editRace.setText(character?.charRace)
        }

        return v
    }

    fun setCharacterGeneral(): Map<String, Editable> {
        return mapOf("charName" to v.editName.text, "charAlignment" to v.editAlignment.text, "charLevel" to v.editLevel.text, "charXP" to v.editXP.text, "charRace" to v.editRace.text)
    }

    companion object {
        fun newInstance(): FragGeneral =
            FragGeneral()
    }

    override fun afterTextChanged(s: Editable?) {
            /*when (s) {
                R.id.editName -> {
                    *//*(activity as CharacterDetails).updateCharacter("charName", editName?.text.toString())*//*
                    Toast.makeText(this.context, "Edit Name", Toast.LENGTH_SHORT).show()
                }
                R.id.editAlignment -> {

                    Toast.makeText(this.context, "Edit Alignment", Toast.LENGTH_SHORT).show()
                }
                R.id.editLevel -> {
                    *//*(activity as CharacterDetails).updateCharacter("charLevel", Integer.parseInt(editLevel?.text.toString()))*//*
                    Toast.makeText(this.context, "Edit Level", Toast.LENGTH_SHORT).show()
                }
                R.id.editRace -> {
                    *//*(activity as CharacterDetails).updateCharacter("editRace", editName?.text.toString())*//*
                }
                R.id.editXP -> {
                    Toast.makeText(this.context, "Edit XP", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this.context, "Other", Toast.LENGTH_SHORT).show()
                }
            }*/
        }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}