package com.example.charactermaster.Fragments

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.charactermaster.Character
import com.example.charactermaster.DnDData
import com.example.charactermaster.R
import kotlinx.android.synthetic.main.fragment_general.view.*
import com.example.charactermaster.DnDRetriever
import kotlinx.android.synthetic.main.fragment_general.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        //check for network connection
        if (isNetworkConnected()) {
            //get weather from API, passing callback functions
            DnDRetriever().get5eClasses(DnDCallback)
        } else {
            //build and output alert dialog
            AlertDialog.Builder(activity?.applicationContext)
                .setTitle("Internet Connection")
                .setMessage("Please check your internet connection")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewGroup = container
        v = inflater.inflate(R.layout.fragment_general, viewGroup, false)

        if (character != null) {
            v.editName.setText(character?.name)
            when(character?.name)
            {
                "Caleb" -> v.imgProfile.setImageResource(R.drawable.caleb)
                "Jester" -> v.imgProfile.setImageResource(R.drawable.jester)
                "Yasha" -> v.imgProfile.setImageResource(R.drawable.yasha)
                "Tanglewood" -> v.imgProfile.setImageResource(R.drawable.tanglewood)
                else -> v.imgProfile.setImageResource(R.drawable.dragonlogo)
            }
            v.editAlignment.setText(character?.alignment)
            v.editLevel.setText(character?.level.toString())
            v.editXP.setText(character?.xp.toString())
            v.editRace.setText(character?.race)
        }

        return v
    }

    private fun isNetworkConnected(): Boolean {
        val networkInfo = (activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }


    private val DnDCallback = object : Callback<DnDData> {
        //function to be executed if API call unsuccessful
        override fun onFailure(call: Call<DnDData>, t: Throwable) {
            Toast.makeText(activity, "Problem with API. Please check the logs.", Toast.LENGTH_LONG).show()
            Log.i("API Error", t.message)
        }

        override fun onResponse(call: Call<DnDData>, response: Response<DnDData>) {
            val classArray = arrayListOf<String>()

            response.body()!!.results.forEach(){
                classArray.add(it.name)
            }

            val a = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_item, classArray)
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinClass.adapter = a
        }
    }


    companion object {
        fun newInstance(): FragGeneral =
            FragGeneral()
    }
}