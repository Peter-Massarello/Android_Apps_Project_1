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
import kotlinx.android.synthetic.main.example_item.*
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity2 : AppCompatActivity() {

    //Variables that will hold info from previous activity
    private var apr = 0.0
    private var loan = 0
    private var escrow = 0.0
    private var year = 0

    // Variables for calculations
    private var numOfMonths = 0
    private var monthlyPayment = 0.0
    private var towardEscrow = 0.0
    private var towardInterest = 0.0
    private var towardPrincipal = 0.0
    private var remainingBalance = 0.0
    private var remainingBalanceEscrow = 0.0

    private val decimal = DecimalFormat("#.##") // format variable used to format to 2 decimal places


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

        // Gather data from intent, defaults set to hint value of text boxes
        var intent = intent
        apr = intent.getDoubleExtra("aprInput", 4.865)
        loan = intent.getIntExtra("loanInput", 10000)
        escrow = intent.getDoubleExtra("escrowInput", 12.50)
        year = intent.getIntExtra("yearInput", 15)

        numOfMonths = year * 12

        calculateMonthlyPayment()

        // Generates recycler view items
        val exampleList = generateMortageList(numOfMonths)

        recycler_view.adapter = ExampleAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    // Calculates monthly payment
    private fun calculateMonthlyPayment(){
        val monthlyInterest = apr / 1200
        val p1 = (1+monthlyInterest).pow(numOfMonths)
        val textView: TextView = findViewById(R.id.monthly_payment)

        decimal.roundingMode = RoundingMode.CEILING

        monthlyPayment = loan * ((monthlyInterest * p1)/(p1 - 1))


        textView.text = "Monthly Payment: ${decimal.format(monthlyPayment)}"
    }

    private fun payTowardInterest(remainingBalance: Double): Double{
        val tempApr = apr / 1200

        return tempApr * remainingBalance
    }

    private fun payTowardPrinciple(towardInterest: Double, monthlyPayment: Double): Double{

        return monthlyPayment - towardInterest
    }

    // Generates all values of recycler view
    private fun generateMortageList(size: Int): List<ExampleItem>{

        val list = ArrayList<ExampleItem>()
        decimal.roundingMode = RoundingMode.CEILING

        remainingBalance = loan.toDouble()

        remainingBalanceEscrow = loan.toDouble() + (escrow * year)

        for (i in 0 until size) {
            val num = i+1
            towardEscrow = escrow / 12
            towardInterest = payTowardInterest(remainingBalance)
            towardPrincipal = payTowardPrinciple(towardInterest, monthlyPayment)

            remainingBalance = remainingBalance - towardPrincipal

            remainingBalanceEscrow = remainingBalanceEscrow - towardPrincipal - towardEscrow

            if (remainingBalanceEscrow < 0)
                    remainingBalanceEscrow = 0.0

            val item = ExampleItem("${decimal.format(remainingBalanceEscrow)}",
                "${decimal.format(towardInterest)}", "${decimal.format(towardPrincipal)}",
                "${decimal.format(towardEscrow)}", "Month $num")
            list += item
        }
        return list
    }
}

