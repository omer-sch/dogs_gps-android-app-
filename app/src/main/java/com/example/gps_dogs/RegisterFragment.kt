package com.example.gps_dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation


class RegisterFragment : Fragment() {

    private var nameTextField: EditText? = null
    private var passwordTextField: EditText?=null
    private var rePasswordTextField: EditText? = null
    private var emailTextField: EditText? = null
    private var registerBtn: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_register, container, false)
        setupUI(view)
        return view
    }

    private fun setupUI(view: View?) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("dogs gps")

        if (view != null) {
            registerBtn = view.findViewById(R.id.signupbtn)
            nameTextField = view.findViewById(R.id.username)
            passwordTextField = view.findViewById(R.id.password)
            emailTextField = view.findViewById(R.id.email)
            rePasswordTextField = view.findViewById(R.id.repassword)


            registerBtn?.setOnClickListener {
                val name = nameTextField?.text.toString()
                val email = emailTextField?.text.toString()
                val password = passwordTextField?.text.toString()


                Model.instance.addUser(name,email,password," ",requireActivity()) {
                    if(it){
                        Toast.makeText(context, "Sign up is successful", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_profileFragment)
                    }
                    else{
                        Toast.makeText(context, "Sign up is failed", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

}