package com.example.charactermaster

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.charactermaster.ui.main.SectionsPagerAdapter

class CharacterDetails : AppCompatActivity() {
    var editable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            editable = !editable
            if(editable)
            {
                fab.setImageDrawable(getDrawable(R.drawable.ic_save))
            }
            else {
                fab.setImageDrawable(getDrawable(R.drawable.ic_edit))
            }
        }
    }
}