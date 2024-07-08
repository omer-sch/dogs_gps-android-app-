package com.example.gps_dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation


class LoginFragment : Fragment() {


    private var nameTextField: EditText? = null
    private var PasswordTextField: EditText? = null
    private var ConnectButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        setupUI(view)
        return view
    }

    private fun setupUI(view: View?) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("dogs gps ")

        if (view != null) {
            nameTextField = view.findViewById(R.id.username)
            PasswordTextField = view.findViewById(R.id.Password)
            ConnectButton = view.findViewById(R.id.Connectbtn)
            ConnectButton?.setOnClickListener {

                val email=nameTextField?.text.toString()
                val password=PasswordTextField?.text.toString()

                Model.instance.login(email,password,requireActivity()){
                    if(it){
                        Toast.makeText(context, " login successful.", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_profileFragment)
                    }
                    else{
                        Toast.makeText(context, " login failed.", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }


    }


}