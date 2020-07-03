package com.example.soptexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sum(a: Int, b: Int): Int {
        Log.d("answer", "answer=${a * b}")
        //Log.d("sum","a + b = ${a+b}")
        return a + b
    }



}