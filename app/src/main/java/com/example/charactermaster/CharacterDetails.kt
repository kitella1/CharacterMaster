package com.example.charactermaster

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.charactermaster.Fragments.FragGeneral
import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetails : AppCompatActivity() {
    var editable: Boolean = false
    var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.putAttrs(intent.getParcelableExtra<Character>("EXTRA_CHAR"), editable)
        viewPager?.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        editable = intent.getBooleanExtra("EXTRA_EDIT", false)

        editToggle()
    }

    fun fabEditButton(view : View?) {
        var frag: FragGeneral = FragGeneral()
        supportFragmentManager.beginTransaction().remove(frag).commit()
    /*if(editable)
        {
            frag.setEditState(false)
            //redraw fragments with new editable field

            //make all fields non-editable
            //save data to local JSON file
                //check if character already exists
                    //if yes, overwrite
                    //if no, add to 'characters' array
        }
        else {
            //make all fields editable
        }*/
        editToggle()
    }

    fun editToggle () {
        if (editable)
        {
            fab.setImageResource(R.drawable.ic_save)
        }
        else
        {
            fab.setImageResource(R.drawable.ic_edit)

        }
        editable = !editable
    }
}

/*pass editable at same place as character, update and redraw on fab click*/