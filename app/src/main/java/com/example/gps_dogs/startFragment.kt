package com.example.gps_dogs

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation


import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class startFragment : Fragment() {

    private var ConnectBtn: Button? = null
    private var SBtn:MaterialButton?=null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        setupUI(view)
        return view
    }

    private fun setupUI(view: View?) {

        if (view != null) {
            ConnectBtn = view.findViewById(R.id.Connectbtn)
            SBtn=view.findViewById(R.id.Registerbtn)
            ConnectBtn?.setOnClickListener {
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    Navigation.findNavController(view).navigate(R.id.action_startFragment_to_profileFragment)
                }
                else{
                    Navigation.findNavController(view).navigate(R.id.action_startFragment_to_loginFragment)
                }
            }
            SBtn?.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_registerFragment)
            }
        }


    }

}