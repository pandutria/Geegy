package com.example.geegy.domain

import android.os.AsyncTask
import android.util.Log
import com.example.geegy.local.support
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class HttpHandler {
    fun getRequest(endpoint: String) : String {
        var response = ""
        try {
            val url = URL(support.url + endpoint)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            response = conn.inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.d("HttpHandler", "Eror : ${e.message}")
        } catch (e: IOException) {
            Log.d("HttpHandler", "Eror : ${e.message}")
        }
        return response
    }
}