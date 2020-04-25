package com.example.charactermaster.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.charactermaster.Character
import com.example.charactermaster.CharacterDetails
import com.example.charactermaster.R
import kotlinx.android.synthetic.main.fragment_general.view.*


private const val CHARACTER = "character"
private const val EDITABLE = "editable"

class FragGeneral : Fragment() {
    private var character: Character? = null
    private var viewGroup: ViewGroup? = null
    private var editable: Boolean = true

    private var editName: EditText? = null
    private val editLevel: EditText? = null
    private val editXP: EditText? = null
    private val editRace: EditText? = null
    private val editAlignment: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getParcelable(CHARACTER);
            editable = it.getBoolean(EDITABLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewGroup = container
        val v: View = inflater.inflate(R.layout.fragment_general, viewGroup, false)

        if (character != null) {
            v.editName.setText(character?.charName)
            v.editAlignment.setText(character?.charAlignment)
            v.editLevel.setText(character?.charLevel.toString())
            v.editXP.setText(character?.charXP.toString())
            v.editRace.setText(character?.charRace)
        }

        setEditState(v)

        return v
    }

   fun setEditState(v: View) {
       v.editName.isFocusable = editable
       if(editable) {
            v.btnUpload.visibility = View.VISIBLE
        }
        else {
            v.btnUpload.visibility = View.GONE
        }
        v.editAlignment.isFocusable = editable
        v.editLevel.isFocusable = editable
        v.editRace.isFocusable = editable
        v.editXP.isFocusable = editable
        v.spinClass.isFocusable = editable

        Log.i("EDITABLE", "reached")
        Log.i("EDITABLE", v.editName.isEnabled.toString())

    }

    companion object {
        fun newInstance(): FragGeneral =
            FragGeneral()
    }
}