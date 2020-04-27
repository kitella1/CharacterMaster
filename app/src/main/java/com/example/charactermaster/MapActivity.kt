package com.example.charactermaster

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
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

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var myLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun isNetworkConnected(): Boolean {
        //get network information
        val networkInfo = (this?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

        //check if device connected to available network
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

                //check for network connection
                if (isNetworkConnected()) {
                    MapRetriever().getNearbyPlaces(MapCallback, myLocation.latitude, myLocation.longitude, 50000, "game", "AIzaSyDoC-CtilUhofsTjfMRtBIvBCMCWGwV1Is")
                } else {
                    //build and output alert dialog
                    AlertDialog.Builder(this?.applicationContext)
                        .setTitle("Internet Connection")
                        .setMessage("Please check your internet connection")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            }

        }
    }

    private val MapCallback = object : Callback<MapData> {
        //function to be executed if API call unsuccessful
        override fun onFailure(call: Call<MapData>, t: Throwable) {
            Toast.makeText(
                this@MapActivity,
                "Problem with API. Please check the logs.",
                Toast.LENGTH_LONG
            ).show()
            Log.i("MapCallback", t.message)
        }

        //function to be executed if API call successful
        override fun onResponse(call: Call<MapData>, response: Response<MapData>) {
            Toast.makeText(this@MapActivity,"Reached", Toast.LENGTH_LONG).show()
            Log.i("Api Response", response.toString())
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
    }
}
