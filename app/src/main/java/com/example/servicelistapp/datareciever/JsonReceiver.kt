package com.example.servicelistapp.datareciever

import android.util.Log
import androidx.annotation.WorkerThread
import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset


class JsonReceiver() {

    private fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
        return this.bufferedReader(charset).use { it.readText() }
    }

    @WorkerThread
    fun getJson(endpoint: String, timeout: Int): String {
        val inputString: String
        val httpURLConnection = URL(endpoint).openConnection() as HttpURLConnection


        httpURLConnection.apply {
            connectTimeout = timeout
            requestMethod = "GET"
            doInput = true
        }

        if (httpURLConnection.responseCode != HttpURLConnection.HTTP_OK) {
            Log.e("Connection", "Parser: ${httpURLConnection.responseCode}")
            return "-1"
        }

        try {
            val input: InputStream = BufferedInputStream(httpURLConnection.inputStream)
            inputString = input.readTextAndClose()
        } catch (exception: Exception) {
            Log.e("Connection", "Parser: ${exception.message}, ${httpURLConnection.responseCode}")
            return "-1"
        }

        httpURLConnection.disconnect()
        Log.i("Connection", "Disconnected!")
        return inputString
    }
}