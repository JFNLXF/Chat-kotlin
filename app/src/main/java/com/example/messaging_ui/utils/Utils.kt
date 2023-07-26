package com.example.messaging_ui.utils

class Utils {

    fun parseTimestamp(time:String): List<String>{
        /**format 2020-03-25T22:36:34.310Z*/

        val parsed = time.split("T")
        val hour = parsed[1].dropLast(8)
        //Log.d("parseTimestamp", hour)

        return listOf(parsed[1], hour)
    }

}