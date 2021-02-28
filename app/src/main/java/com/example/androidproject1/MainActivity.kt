package com.example.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeoutException

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    
    lateinit var model: MainViewModel
    lateinit var binding: ActivityMainBinding
    var aprFlag = false
    var escrowFlag = false
    var loanFlag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        var submitFlag: Boolean = false

        val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.year_array,
                android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        val provider = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
        model = provider.get(MainViewModel::class.java)

        binding.aprInput.doAfterTextChanged {
            aprFlag = validateAPRText(binding.aprInput)
            submitFlagCheck()
        }
        binding.escrowInput.doAfterTextChanged {
            escrowFlag = validateEscrowText(binding.escrowInput)
            submitFlagCheck()
        }
        binding.loanInput.doAfterTextChanged {
            loanFlag = validateLoanText(binding.loanInput)
            submitFlagCheck()
        }

        submitButton.setOnClickListener(submitButtonClicked)

        clearButton.setOnClickListener(clearButtonClicked)
        spinner.onItemSelectedListener = this
    }

    private val clearButtonClicked = View.OnClickListener { // Once clicked, goes to clear form function
        clearAllForms()
    }

    private val submitButtonClicked = View.OnClickListener { // Once clicked, goes to clear form function
        passToNextAct()
    }

    private fun passToNextAct(){
        Log.e("Item", "${aprFlag}")
    }

    private fun submitFlagCheck(){
        binding.submitButton.isEnabled = aprFlag && escrowFlag && loanFlag
    }

    private fun clearAllForms(){ //Resets all Input Forms
        spinner.setSelection(0)
        apr_input.text.clear()
        escrow_input.text.clear()
        loan_input.text.clear()
    }

   private fun validateAPRText(textView: TextView): Boolean{
       val input = textView.text.toString()

       if (input.length < 1) {
           apr_input.error = "Field cannot be blank."
           return false
       }
       val pattern = "[0-9]+(\\.[0-9]?[0-9]?[0-9]?)".toRegex()
       if(pattern.matches(input)){
           return true
       }
       else {
           apr_input.error = "Must have up to 3 decimal places"
           return false
       }
       return false
    }

    private fun validateEscrowText(textView: TextView): Boolean{
        val input = textView.text.toString()

        if (input.length < 1) {
            escrow_input.error = "Field cannot be blank."
            return false
        }

        val pattern = "[0-9]+(\\.[0-9]?[0-9]?)".toRegex()
        if(pattern.matches(input)){
            return true
        }
        else {
            escrow_input.error = "Must have up to 3 decimal places"
            return false
        }
        return false
    }
    private fun validateLoanText(textView: TextView): Boolean{
       val input = textView.text.toString()

        if (input.length < 1) {
            loan_input.error = "Field cannot be blank."
            return false
        }
        val pattern = "\\d*".toRegex()
        if(pattern.matches(input)){
            return true
        }
        else {
            loan_input.error = ""
            return false
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        Log.e("Item", "${spinner.selectedItem.toString().toInt()}")
    }

}


