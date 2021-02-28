package com.example.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var intent = intent
        val apr = intent?.getDoubleExtra("apr", 0.0)
        val loan = intent?.getIntExtra("loan", 0)
        val escrow = intent?.getDoubleExtra("escrow", 0.0)
        val year = intent?.getIntExtra("year", 0)

        Log.e("values", "$apr $loan $escrow $year")
    }
}

