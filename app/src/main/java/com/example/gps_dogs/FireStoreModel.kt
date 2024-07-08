package com.example.gps_dogs

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FireStoreModel {

    var storageRef = Firebase.storage.getReference()
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseref: DatabaseReference
    private lateinit var dbimage: StorageReference
    val db = Firebase.firestore


    companion object {
        const val USERS_COLLECTION_PATH = "users"
        const val GPS_DATA_COLLECTION_PATH="gps_data"
    }
    init {
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
        db.firestoreSettings = settings
        auth = Firebase.auth
        dbimage=FirebaseStorage.getInstance().getReference("images")
    }

    fun getAllData(callback: (List<Gps_data>) -> Unit) {
        db.collection(GPS_DATA_COLLECTION_PATH).get().addOnCompleteListener {
            when (it.isSuccessful) {
                true -> {
                    val list: MutableList<Gps_data> = mutableListOf()
                    for (json in it.result) {
                        val gpsData = Gps_data.fromJSON(json.data)
                        list.add(gpsData)
                    }

                    callback(list)
                }
                false -> callback(listOf())
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodaysData(callback: (List<Gps_data>) -> Unit) {
        db.collection(GPS_DATA_COLLECTION_PATH).get().addOnCompleteListener {
            when (it.isSuccessful) {
                true -> {
                    val list: MutableList<Gps_data> = mutableListOf()
                    for (json in it.result) {
                        val gpsData = Gps_data.fromJSON(json.data)
                        list.add(gpsData)
                    }
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val today = LocalDate.now()
                    callback( list.filter {
                        LocalDateTime.parse(it.TimeStamp, formatter).toLocalDate() == today
                    }
                        .sortedBy { LocalDateTime.parse(it.TimeStamp, formatter) })
                }
                false -> callback(listOf())
            }
        }
    }



    fun addUser(name: String, email: String, password: String, uri: String, activity:Activity, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    var id: String = "null"
                    if (auth.currentUser != null) {
                       id = auth.currentUser!!.uid
                    }

                    val user = User(name, id, email, password, uri, "",false)

                    db.collection(USERS_COLLECTION_PATH).document(user.id).set(user.json).addOnSuccessListener {

                        callback(true)
                    }
                }
            }

    }
    fun updateUser(name: String,uri: String,Bio:String,callback: () -> Unit) {
        val user = Firebase.auth.currentUser

        if (user != null) {

          //  dbimage.child(user.uid).putFile(uri.toUri()).addOnSuccessListener { task ->
              //  task.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                    db.collection(USERS_COLLECTION_PATH).document(user.uid).set(
                        mapOf(
                            "name" to name,
                            "Bio" to Bio,
                            "uri" to "it",
                            "lastUpdated" to FieldValue.serverTimestamp()
                        ), SetOptions.merge()
                    ).addOnCompleteListener {
                        callback()
                    }
              //  }
          //  }
        }

    }
    fun getCurrentUser(callback: (List<String>) -> Unit){
        var userId: String=auth.currentUser!!.uid
        db.collection(USERS_COLLECTION_PATH).whereEqualTo("id", userId).get().addOnCompleteListener {
            when (it.isSuccessful) {
                true -> {
                    val users: MutableList<String> = mutableListOf()

                    for (json in it.result) {
                        val user = User.fromJSON(json.data)
                        users.add(user.name)
                        users.add(user.email)
                        users.add(user.bio)
                        users.add(user.uri)
                        callback(users)
                    }

                }
                false ->callback(listOf<String>())
            }
        }
    }





    fun login(email:String,password:String,activity:Activity, callback: (Boolean) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    callback(true)
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    callback(false)
                    // Toast.makeText(context, "Failed to login user.", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun signOut(){
        auth.signOut()
    }

}