package com.example.charactermaster

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.example.charactermaster.mapAPI.MapData
import com.example.charactermaster.mapAPI.MapRetriever
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    SensorEventListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var myLocation: Location
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        GOOGLE_MAPS_KEY = resources.getString(R.string.google_maps_key)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun isNetworkConnected(): Boolean {
        val networkInfo = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)

        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                myLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))

                if (isNetworkConnected()) {
                    MapRetriever().getNearbyPlaces(mapCallback, myLocation.latitude, myLocation.longitude, 50000, "game", GOOGLE_MAPS_KEY)
                } else {
                    AlertDialog.Builder(this.applicationContext)
                        .setTitle("Internet Connection")
                        .setMessage("Please check your internet connection")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            }
        }
    }

    private val mapCallback = object : Callback<MapData> {
        override fun onFailure(call: Call<MapData>, t: Throwable) {
            Toast.makeText(
                this@MapActivity,
                "Problem with API. Please check the logs.",
                Toast.LENGTH_LONG
            ).show()
            Log.i("MapCallback", t.message)
        }

        override fun onResponse(call: Call<MapData>, response: Response<MapData>) {
            Toast.makeText(this@MapActivity,"Reached", Toast.LENGTH_LONG).show()
            val results = response.body()!!.results

            for(result in results) {
                val r = LatLng(result.geometry.location.lat, result.geometry.location.lng)
                map.addMarker(MarkerOptions().position(r).title(result.name))
            }
        }
    }

    override fun onMarkerClick(p0: Marker?)= false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private var GOOGLE_MAPS_KEY = ""
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
