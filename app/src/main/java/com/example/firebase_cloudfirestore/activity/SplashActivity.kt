package com.example.firebase_cloudfirestore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_cloudfirestore.R
import java.lang.Exception

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background : Thread = object : Thread(){
            override fun run() {
                try {
                    //Thread will sleep for 2 seconds
                    sleep((2 * 1000).toLong())

                    val signInIntent = Intent(baseContext, MainActivity::class.java)
                    startActivity(signInIntent)

                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        // start thread
        background.start()
    }
}