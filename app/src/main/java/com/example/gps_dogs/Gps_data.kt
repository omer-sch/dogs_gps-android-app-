package com.example.gps_dogs

data class Gps_data(val TimeStamp: String,
                    val latitude: Double,
                    val longitude: Double) {


    companion object {
        const val TIMESTAMP_KEY = "timestamp"
        const val LATITUDE_KEY = "latitude"
        const val LONGITUDE_KEY = "longitude"


        fun fromJSON(json: Map<String, Any>): Gps_data {

            val TimeStamp = json[TIMESTAMP_KEY] as? String ?: ""
            val latitude = json[LATITUDE_KEY] as? Double ?: 0.0
            val longitude = json[LONGITUDE_KEY] as? Double ?: 0.0
            return Gps_data(TimeStamp, latitude, longitude)
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                TIMESTAMP_KEY to TimeStamp,
                LATITUDE_KEY to latitude,
                LONGITUDE_KEY to longitude,
            )
        }
}
