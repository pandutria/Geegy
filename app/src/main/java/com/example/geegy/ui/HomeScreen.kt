package com.example.geegy.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.geegy.R
import com.example.geegy.domain.DataParsing.DataParsingAppointment

class HomeScreen : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var etDate: EditText
    lateinit var etSearch: EditText
    lateinit var tvSuccess: TextView
    lateinit var tvCancel: TextView

    var cancel: Int = 0
    var success: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        rv = findViewById(R.id.rv)
        etDate = findViewById(R.id.etDate)
        etSearch = findViewById(R.id.etSearch)
        tvSuccess = findViewById(R.id.tvSuccessAppointment)
        tvCancel = findViewById(R.id.tvCancelledAppointment)


        etDate.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

//                tvSuccess.text.isEmpty()
//                var input = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//                var output = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//                var search = input.parse(etSearch.text.toString())
//                var formatedSearch = output.format(search!!)

                DataParsingAppointment(this@HomeScreen, rv, etDate.text.toString(), etSearch.text.toString()).execute()

            }

            override fun afterTextChanged(p0: Editable?) {
//                tvCancel.text = cancel.toString()
//                tvSuccess.text = success.toString()
            }
        })

        etSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                DataParsingAppointment(this@HomeScreen, rv, etDate.text.toString(), etSearch.text.toString()).execute()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


    }


}