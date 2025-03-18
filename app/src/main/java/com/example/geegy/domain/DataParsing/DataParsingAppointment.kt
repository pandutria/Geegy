package com.example.geegy.domain.DataParsing

import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geegy.ui.HomeScreen
import com.example.geegy.adapter.AppointmentAdapter
import com.example.geegy.domain.HttpHandler
import com.example.geegy.model.Appointment
import org.json.JSONArray

class DataParsingAppointment(private val context: HomeScreen, private val rv: RecyclerView, private val date: String, private val search: String): AsyncTask<Void, Void,Void>() {
    val appointmenList: MutableList<Appointment> = arrayListOf()
    override fun doInBackground(vararg p0: Void?): Void? {
        try {
            var jsonToUrl = HttpHandler().getRequest("appointments?date=$date")

            if (search != null) {
                jsonToUrl = HttpHandler().getRequest("appointments?date=$date&&search=$search")
            }

            var jsonToArray = JSONArray(jsonToUrl)

            for (i in 0 until jsonToArray.length()) {
                var appointment = jsonToArray.getJSONObject(i)
                var id = appointment.getInt("id")
                var patient_name = appointment.getString("patient_name")
                var patient_dob = appointment.getString("patient_dob")
                var patient_age = appointment.getInt("patient_age")
                var problems = appointment.getString("problems")
                var appointment_date = appointment.getString("appointment_date")
                var iscancelled = appointment.getBoolean("iscancelled")

                appointmenList.add(Appointment(id, patient_name, patient_dob, patient_age, problems, appointment_date, iscancelled))
            }
        } catch (e: Exception) {
            Log.d("HttpHandler", "Eror : ${e.message}")
        }
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        if (result.toString().isNotEmpty()) {
            rv.adapter = AppointmentAdapter(appointmenList)
            rv.layoutManager = LinearLayoutManager(context)

            context.cancel = 0
            context.success = 0

            for (i in appointmenList) {
                if (i.iscancelled == true) {
                    context.cancel++

                    context.runOnUiThread {
                        context.tvCancel.text = "Cancelled Appointment:" + context.cancel.toString()
                    }
                }

                if (i.iscancelled == false){
                    context.success++

                    context.runOnUiThread {
                        context.tvSuccess.text = "Success Appointment:" + context.success.toString()
                    }
                }
            }
        }
    }
}