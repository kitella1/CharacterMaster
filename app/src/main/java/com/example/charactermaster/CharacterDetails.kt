package com.example.charactermaster

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.android.synthetic.main.fragment_general.*

class CharacterDetails : AppCompatActivity(), SensorEventListener {
    var character: Character? = null
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        character = intent.getParcelableExtra<Character>("EXTRA_CHAR")

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.putAttrs(character)
        viewPager?.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
    }

    fun fabSave(view : View?) {
        val char = Character(editName.text.toString(), "", spinClass.selectedItem.toString(), Integer.parseInt(editLevel.text.toString()), Integer.parseInt(editXP.text.toString()), editAlignment.text.toString(), editRace.text.toString(), txtBackground.text.toString(), txtAppearance.text.toString(), txtTraits.text.toString(), txtIdeals.text.toString(), txtBonds.text.toString(), txtFlaws.text.toString())
        val storage: Storage = Storage(this, char.name!!)
        storage.saveCharacter(char)

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
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