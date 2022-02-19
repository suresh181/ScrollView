package com.example.scrollviewlistview

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.scrollviewlistview.databinding.ActivityMainBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val gender = resources.getStringArray(R.array.gender)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,gender)
        binding.spGender.adapter = adapter

        val hsc = arrayOf("","12th","Diploma")
        val hscAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,hsc)
        binding.spHsc.adapter = hscAdapter

        val degree = resources.getStringArray(R.array.degree)
        val degreeAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,degree)
        binding.atvDegree.threshold = 1
        binding.atvDegree.setAdapter(degreeAdapter)

        val percentage = resources.getStringArray(R.array.percentage)
        val percentageAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,percentage)
        binding.atvDegreePer.threshold = 1
        binding.atvDegreePer.setAdapter(percentageAdapter)


        binding.etEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches())
                    Toast.makeText(this@MainActivity,"Valid Email Address",Toast.LENGTH_SHORT).show()
                else{
                    binding.etEmail.setError("Invalid Email")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.etMobile.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (mobileValid(binding.etMobile.text.toString()))
                    Toast.makeText(this@MainActivity,"Valid Number",Toast.LENGTH_SHORT).show()
                else{
                    binding.etMobile.setError("Invalid Number")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


    }

    private fun mobileValid(text: String?): Boolean {
        val pattern:Pattern = Pattern.compile("[6-9][0-9]{9}")
        val matcher:Matcher = pattern.matcher(text)
        return matcher.matches()

    }


}