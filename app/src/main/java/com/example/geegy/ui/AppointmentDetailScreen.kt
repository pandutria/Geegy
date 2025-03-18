package com.example.geegy.ui

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geegy.R
import com.example.geegy.domain.HttpHandler
import com.example.geegy.local.support
import org.json.JSONObject

class AppointmentDetailScreen : AppCompatActivity() {

    var id: Int = 0

    lateinit var tvDate: TextView
    lateinit var tvPatientName: TextView
    lateinit var tvPatientDob: TextView
    lateinit var tvPatientAddress: TextView
    lateinit var tvDoctorName: TextView
    lateinit var tvDoctorSpec: TextView
    lateinit var tvProblems: TextView
    lateinit var tvSymptoms: TextView
    lateinit var tvAction: TextView
    lateinit var imgPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_detail_screen)

        tvDate = findViewById(R.id.tvDate)
        tvPatientName = findViewById(R.id.tvPatientName)
        tvPatientDob = findViewById(R.id.tvPatientDob)
        tvPatientAddress = findViewById(R.id.tvPatientAddress)
        tvDoctorName = findViewById(R.id.tvDoctorName)
        tvDoctorSpec = findViewById(R.id.tvDoctorSpec)
        tvProblems = findViewById(R.id.tvProblems)
        tvSymptoms = findViewById(R.id.tvSymptoms)
        tvAction = findViewById(R.id.tvAction)
        imgPhoto = findViewById(R.id.imgPhoto)
        id = intent.getIntExtra("id",0)

        appointmentAsycnTask(this, id).execute()

        findViewById<ImageView>(R.id.back).setOnClickListener {
            finish()
        }

    }

    private class appointmentAsycnTask(private val activity: AppointmentDetailScreen, private val id: Int): AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String?): String {
            return HttpHandler().getRequest("appointments/$id")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result.toString().isNotEmpty()) {
                var appointment = JSONObject(result)

                activity.runOnUiThread {
                    activity.tvDate.text = appointment.getString("appointment_date")

                    activity.tvPatientName.text = "Name: " + appointment.getString("patient_name")
                    activity.tvPatientDob.text = "DOB: " + appointment.getString("patient_dob") + " (${appointment.getString("patient_age")} years old)"
                    activity.tvPatientAddress.text = "Address: " + appointment.getString("patient_address")

                    activity.tvDoctorName.text = "Name: " + appointment.getString("doctor_name")
                    activity.tvDoctorSpec.text = "Specialist: " + appointment.getString("doctor_specialist")

                    activity.tvProblems.text = appointment.getString("problems")
                    activity.tvSymptoms.text = appointment.getString("symptoms")
                    activity.tvAction.text = appointment.getString("actions")
                    support.showImage(activity.imgPhoto, appointment.getString("teeth_photo"))

                }

            }
        }
    }

}