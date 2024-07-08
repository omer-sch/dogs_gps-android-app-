package com.example.gps_dogs

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi

class Model private constructor(){

    private val firebaseModel = FireStoreModel()

    companion object {
        val instance: Model = Model()
    }

    fun addUser(name:String,email: String,password: String,uri: String,activity: Activity ,callback: (Boolean) -> Unit) {

        firebaseModel.addUser(name, email, password, uri, activity, callback)
    }
    fun updateUser(name: String,uri: String,bio:String,callback: () -> Unit){
        firebaseModel.updateUser(name,uri,bio,callback)
    }
    fun login(email:String, password:String, activity: Activity, callback: (Boolean) -> Unit){
        firebaseModel.login(email,password,activity ,callback)
    }
    fun signOut(){
        firebaseModel.signOut()
    }
    fun getCurrentUser(callback: (List<String>) -> Unit){
        firebaseModel.getCurrentUser(callback)
    }
    fun getAllData(callback: (List<Gps_data>) -> Unit) {
        firebaseModel.getAllData(callback)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodaysData(callback: (List<Gps_data>) -> Unit){
        firebaseModel.getTodaysData(callback)
    }
}