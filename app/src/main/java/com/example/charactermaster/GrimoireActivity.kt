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

        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        recyclerView.adapter = CharacterAdapter(this, chars).apply {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        }

        val refresher: SwipeRefreshLayout = pullToRefresh
        refresher.setOnRefreshListener {
            val store = Storage(this)
            val characters = store.loadCharacters()
            recyclerView.adapter = CharacterAdapter(this, characters).apply {
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
            sensorManager.registerListener(this, light,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //Toast.makeText(this, "Accuracy change", Toast.LENGTH_LONG).show()
    }

    override fun onSensorChanged(event: SensorEvent) {
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