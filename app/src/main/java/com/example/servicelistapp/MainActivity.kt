package com.example.servicelistapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicelistapp.adapter.RecyclerAdapter
import com.example.servicelistapp.datareciever.JsonParser
import com.example.servicelistapp.datareciever.JsonReceiver
import com.example.servicelistapp.datareciever.Service
import java.lang.Exception

class MainActivity : AppCompatActivity(), RecyclerAdapter.Listener {
    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread() {
            var serviceData = ArrayList<Service>()


            while (serviceData.isEmpty()) {
                try {
                    val jsonData = JsonReceiver().getJson(
                        getString(R.string.data_url),
                        getString(R.string.timeout).toInt()
                    )
                    serviceData = JsonParser().parseJson(jsonData)
                } catch (exception: Exception) {
                    runOnUiThread() {
                        Toast.makeText(applicationContext, "Ошибка загрузки", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("Connection", exception.message.toString())
                    Thread.sleep(5000)
                }
            }


            runOnUiThread() {
                val recyclerView: RecyclerView = findViewById(R.id.service_recycler)
                recyclerView.adapter = RecyclerAdapter(serviceData, this)
            }
        }.start()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(service: Service) {
        val serviceDialog = Dialog(this)
        serviceDialog.setContentView(R.layout.service_fragment)

        serviceDialog.findViewById<TextView>(R.id.name_fragment).text = service.name
        serviceDialog.findViewById<TextView>(R.id.description_fragment).text = service.description
        serviceDialog.findViewById<TextView>(R.id.link_fragment).text = service.service_url


        val closeButton = serviceDialog.findViewById<ImageButton>(R.id.closeButton)
        closeButton.setOnClickListener {
            serviceDialog.dismiss()
        }

        Glide.with(serviceDialog.context).load(service.icon_url)
            .into(serviceDialog.findViewById(R.id.icon_fragment))

        serviceDialog.show()
    }
}