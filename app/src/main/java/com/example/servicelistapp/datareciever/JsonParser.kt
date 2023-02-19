package com.example.servicelistapp.datareciever

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class JsonParser {

    fun parseJson(input: String) : ArrayList<Service> {
        val result: ArrayList<Service> = ArrayList()
        var serviceJSONArray : JSONArray

        if (input == "-1"){
            return result
        }


        if (JSONObject(input).getJSONArray("items") != null){
            serviceJSONArray = JSONObject(input).getJSONArray("items")
        } else {
            Log.e("Parser", "Parse error!")
            return result
        }


        for (i in 0 until serviceJSONArray.length()) {
            val obj = serviceJSONArray.getJSONObject(i)
            result.add(
                Service(
                    obj.getString("name"),
                    obj.getString("description"),
                    obj.getString("icon_url"),
                    obj.getString("service_url")
                )
            )
        }

        return result
    }
}