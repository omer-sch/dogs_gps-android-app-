package com.example.gps_dogs

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation


class edit_ProfileFragment : Fragment() {

    private var saveButton: Button? = null
    private var UploadButton: ImageButton? = null
    private var nameEdit: EditText?=null
    private var bioEdit: EditText?=null
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit__profile, container, false)

        setupUI(view)
        return view
    }

    private fun setupUI(view: View?) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle("Profile")

        if (view != null) {
            saveButton = view.findViewById(R.id.saveProfileBtn)
            UploadButton=view.findViewById(R.id.uploadPhotoBtn)
            nameEdit = view.findViewById(R.id.username)
            bioEdit = view.findViewById(R.id.bio)


            saveButton?.setOnClickListener {
                val name=nameEdit?.text.toString()
                val bio=bioEdit?.text.toString()

                Model.instance.updateUser(name," ",bio){}
                Navigation.findNavController(view).navigate(R.id.action_edit_ProfileFragment_to_profileFragment)
            }
            val pickImage= registerForActivityResult(ActivityResultContracts.GetContent()) {
                // Picasso.get().load(it).resize(5000, 5000).centerInside().into(imageView)

                if (it != null) {
                    var uri = it.toString()
                }
            }
            UploadButton?.setOnClickListener {
                pickImage.launch("image/*")
            }



        }
    }

//    fun pickImage() {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(intent, PICK_IMAGE_REQUEST)
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
//            val selectedImage: Uri? = data.data
//            // Use the selectedImage Uri to display or process the image
//        }
//    }
}