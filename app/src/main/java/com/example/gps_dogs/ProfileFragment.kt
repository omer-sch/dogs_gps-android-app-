package com.example.gps_dogs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.Navigation


class ProfileFragment : Fragment() {


    private var MapButton: Button? = null
    private var EditButton: ImageButton? = null
    private var nameTextView: TextView? = null
    private var emailTextView: TextView? = null
    private var bioTextView: TextView? = null
    private var name:String?=null
    private var email:String?=null
    private var bio:String?=null
    private var uri:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        setupUI(view)
        return view
    }

    private fun setupUI(view: View?) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("Profile")


        if (view != null) {
            EditButton = view.findViewById(R.id.editProfileBtn)
            MapButton = view.findViewById(R.id.mapButton)
            nameTextView=view.findViewById((R.id.username))
            emailTextView=view.findViewById((R.id.email))
            bioTextView=view.findViewById((R.id.bio))

            EditButton?.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_edit_ProfileFragment)

            }
            MapButton?.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_mapsActivity)

            }

            Model.instance.getCurrentUser{
                Toast.makeText(context, " we got here.", Toast.LENGTH_SHORT).show()
                name= it[0]
                email= it[1]
                bio=it[2]
                uri=it[3]

                nameTextView?.text = name
                emailTextView?.text = email
                bioTextView?.text = email

            }

        }
    }




}