package com.example.charactermaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_grimoire.*

class GrimoireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grimoire)
        var context = this

        val characterList = Character.getCharacters("characters.json", this)

        //link to recycler view

        recyclerView.adapter = CharacterAdapter(this, characterList).apply {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}