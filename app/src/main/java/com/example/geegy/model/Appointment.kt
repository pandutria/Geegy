package com.example.geegy.model

data class Appointment(
    var id: Int,
    var patient_name: String,
    var patient_dob: String,
    var patient_age: Int,
    var problems: String,
    var appointment_date: String,
    var iscancelled: Boolean
)
