package com.example.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.year_array,
                android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        clearButton.setOnClickListener(clearButtonClicked)
        //spinner.onItemSelectedListener(spinnerItemSelected)

    }

    private val clearButtonClicked = View.OnClickListener {
        clearAllForms() // Once clicked, goes to clear form function
    }

    private fun clearAllForms(){
        /*
         * Needs to reset spinner input as well
         */
        apr_input.getText().clear()
        escrow_input.getText().clear()
        loan_input.getText().clear()
    }
}

