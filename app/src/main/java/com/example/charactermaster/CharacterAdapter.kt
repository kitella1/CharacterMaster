package com.example.charactermaster

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.list_view_character.view.*

class CharacterAdapter(private val context: GrimoireActivity, private val characters: ArrayList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view_character, parent, false))
    }
    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.charName.text = characters.get(position).charName
        holder.charRace.text = "Race: " + characters.get(position).charRace
        holder.charClass.text = "Class: " + characters.get(position).charClass
        holder.charLevel.text = "Level: " + characters.get(position).charLevel.toString()
        val resourceId = context.resources.getIdentifier("@drawable/" + characters.get(position).profileImage,"drawable", context.packageName)
        holder.charProfileImage.setImageResource(resourceId)

        holder.itemView.setOnClickListener {
            val characterIntent: Intent = Intent(context, GrimoireActivity::class.java).apply {
                putExtra("EXTRA_NAME", characters.get(position).charName)
                putExtra("EXTRA_RACE", characters.get(position).charRace)
                putExtra("EXTRA_CLASS", characters.get(position).charClass)
                putExtra("EXTRA_LVL", characters.get(position).charLevel)
                putExtra("EXTRA_IMG", resourceId)
            }
            startActivity(context, characterIntent, null)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val charName = view.txtName
        val charRace = view.txtRace
        val charClass = view.txtClass
        val charLevel = view.txtLevel
        val charProfileImage = view.imgProfileImage
    }
}