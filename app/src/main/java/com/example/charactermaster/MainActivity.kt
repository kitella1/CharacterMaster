package com.example.charactermaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun viewGrimoire (x: View?) {
        val grimIntent = Intent(this, GrimoireActivity::class.java)
        startActivity(grimIntent)
    }

    fun newCharacter (x: View?) {
        val newCharIntent = Intent(this, CharacterDetails::class.java)
        startActivity(newCharIntent)
    }



}

