package com.example.geegy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.geegy.R
import com.example.geegy.model.Appointment
import com.example.geegy.ui.AppointmentDetailScreen
import java.text.SimpleDateFormat
import java.util.Locale

class AppointmentAdapter(private val appointmentList: List<Appointment>): RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvDob = itemView.findViewById<TextView>(R.id.tvDob)
        val tvProblem = itemView.findViewById<TextView>(R.id.tvProblem)
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        val card = itemView.findViewById<CardView>(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.tvName.text = appointment.patient_name

        val inputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val date = inputFormat.parse(appointment.appointment_date)
        val formateDate = outputFormat.format(date!!)

//        val dob = inputFormat.parse(appointment.patient_dob)
//        val formateDob = outputFormat.format(dob!!)

        holder.tvDob.text = "DOB: ${appointment.patient_dob} (${appointment.patient_age} years old)"
        holder.tvProblem.text = appointment.problems
        holder.tvDate.text = formateDate

        if (appointment.iscancelled == true) {
            holder.card.visibility = View.GONE
        } else {
            holder.card.visibility = View.VISIBLE
        }

//        if (!(appointment.iscancelled)) {
//            holder.card.visibility = View.VISIBLE
//        } else {
//            holder.card.visibility = View.GONE
//        }

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, AppointmentDetailScreen::class.java)
            i.putExtra("id", appointment.id)
            holder.itemView.context.startActivity(i)
        }

    }
}