package com.example.charactermaster

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.content_grimoire.*
import org.json.JSONException
import org.json.JSONObject

class GrimoireActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grimoire)

        val storage = Storage(this)
        val chars = storage.loadCharacters()

        //create instance of SensorManager class
        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        //get the default light sensor
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        //link to recycler view
        recyclerView.adapter = CharacterAdapter(this, chars).apply {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        }

        val refresher: SwipeRefreshLayout = pullToRefresh
        refresher.setOnRefreshListener {
            val storage = Storage(this)
            val chars = storage.loadCharacters()
            recyclerView.adapter = CharacterAdapter(this, chars).apply {
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            }
            (recyclerView.adapter as CharacterAdapter).notifyDataSetChanged()
            pullToRefresh.isRefreshing = false
        }
    }

    fun fabAddCharacter(view: View) {
        val newCharIntent = Intent(this, CharacterDetails::class.java)
        startActivity(newCharIntent)
    }

    override fun onResume() {
        super.onResume()
        light?.let { light ->
            //add listener with default sampling interval
            sensorManager.registerListener(this, light,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        //remove listener
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //code to be executed when accuracy changes
        //not needed if accuracy is not a factor
        //Toast.makeText(this, "Accuracy change", Toast.LENGTH_LONG).show()
    }

    override fun onSensorChanged(event: SensorEvent) {
        //light sensor returns a single value

        val lux = event.values[0]
        //value based on https://www.engineeringtoolbox.com/light-level-rooms-d_708.html
        if(lux < 100)
        {
            applyAppTheme("dark")
        }
        else
        {
            applyAppTheme("light")
        }
    }

    private fun applyAppTheme(theme: String) {
        when (theme) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}