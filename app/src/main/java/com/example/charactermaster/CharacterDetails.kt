package com.example.charactermaster

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.android.synthetic.main.fragment_general.*

class CharacterDetails : AppCompatActivity() {
    var editable: Boolean = false
    var character: Character? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        character = intent.getParcelableExtra<Character>("EXTRA_CHAR")

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.putAttrs(character)
        viewPager?.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)


    }

    fun fabSave(view : View?) {
        val char = Character(editName.text.toString(), "", "", Integer.parseInt(editLevel.text.toString()), Integer.parseInt(editXP.text.toString()), editAlignment.text.toString(), editRace.text.toString(), txtBackground.text.toString(), txtAppearance.text.toString(), txtTraits.text.toString(), txtIdeals.text.toString(), txtBonds.text.toString(), txtFlaws.text.toString())
        val storage: Storage = Storage(this, char?.charName!!)
        storage.saveCharacter(char!!)

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
    }
}