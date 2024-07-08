package com.example.gps_dogs

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.gps_dogs.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Model.instance.getTodaysData{
            val latLngList: List<LatLng> = it.map { LatLng(it.latitude, it.longitude) }
            val polyline1 = googleMap.addPolyline(
                PolylineOptions()
                    .clickable(true).addAll(latLngList));
            if(!latLngList.isEmpty()){
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngList[0], 15f))
                googleMap.addMarker(MarkerOptions().position(latLngList[latLngList.size-1]).title("kira"))

            }

        }

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this)
        googleMap.setOnPolygonClickListener(this)
    }

    override fun onPolylineClick(p0: Polyline) {
        TODO("Not yet implemented")
    }

    override fun onPolygonClick(p0: Polygon) {
        TODO("Not yet implemented")
    }
}