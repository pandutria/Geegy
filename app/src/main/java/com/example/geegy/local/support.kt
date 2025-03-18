package com.example.geegy.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.AsyncTask
import android.widget.ImageView
import android.widget.Toast
import java.net.URL

object support {
    var url = "http://10.0.2.2:5000/api/"
    var urlImage = url.replace("api/", "images/")

    fun showImage(imageView: ImageView, imageData: String) {
        imageAsycnTask(imageView).execute(urlImage + imageData)
    }

    private class imageAsycnTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {
        override fun doInBackground(vararg p0: String?): Bitmap? {
            return try {

                var input = URL(p0[0]).openStream()
                BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }
}