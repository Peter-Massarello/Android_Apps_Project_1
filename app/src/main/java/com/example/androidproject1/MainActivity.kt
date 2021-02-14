package com.example.androidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    


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
        apr_input.doAfterTextChanged {
            validateText(3, R.id.apr_input);
        }
        clearButton.setOnClickListener(clearButtonClicked)

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

    private fun validateText(decimalCheck: Int, @IdRes viewId: Int){
        //val temp: EditText = findViewById(R.id.viewId)
        val input = apr_input.text.toString()
        val wholeNumbers: Int = input.indexOf('.')
        val decimalCount: Int = input.length - wholeNumbers - 1

        if (decimalCount > decimalCheck){
            apr_input.error = "Can only be to $decimalCheck decimal places."
        }

    }



    /*private fun EditText.onTextChange(onTextChange: (String) -> Unit){
        this.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                onTextChange.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }*/

}

