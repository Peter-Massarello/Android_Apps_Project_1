package com.example.androidproject1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity2 : AppCompatActivity() {

    private var apr = 0.0
    private var loan = 0
    private var escrow = 0.0
    private var year = 0

    private var numOfMonths = 0
    private var monthlyPayment = 0.0

    companion object{
        fun newIntent(context: Context, aprInput: Double, loanInput: Int, yearInput: Int, escrowInput: Double): Intent{
            val intent: Intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("aprInput", aprInput)
            intent.putExtra("loanInput", loanInput)
            intent.putExtra("yearInput", yearInput)
            intent.putExtra("escrowInput", escrowInput)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var intent = intent
        apr = intent.getDoubleExtra("aprInput", 4.865)
        loan = intent.getIntExtra("loanInput", 10000)
        escrow = intent.getDoubleExtra("escrowInput", 12.50)
        year = intent.getIntExtra("yearInput", 15)

        numOfMonths = year * 12

        calculateMonthlyPayment()

        val exampleList = generateMortageList(numOfMonths)

        recycler_view.adapter = ExampleAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun calculateMonthlyPayment(){
        val monthlyInterest = apr / 1200
        val p1 = (1+monthlyInterest).pow(numOfMonths)
        val textView: TextView = findViewById(R.id.monthly_payment)
        val decimal = DecimalFormat("#.##")
        decimal.roundingMode = RoundingMode.CEILING

        monthlyPayment = loan * ((monthlyInterest * p1)/(p1 - 1))


        textView.text = "Monthly Payment: ${decimal.format(monthlyPayment)}"
    }

    private fun towardInterest(){


    }

    private fun towardPrinciple(){


    }

    private fun towardEscrow(){


    }

    private fun remainingBalance(){


    }

    private fun generateMortageList(size: Int): List<ExampleItem>{

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val num = i+1
            val item = ExampleItem("$apr", "$loan", "$escrow", "$year", "Month $num")
            list += item
        }
        return list
    }
}

