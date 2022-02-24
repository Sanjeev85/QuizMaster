package com.example.quizmaster.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login_activity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        login.setOnClickListener {
            loginUser()
        }
        signup_now.setOnClickListener {
            val intent = Intent(applicationContext, signUp_activity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun loginUser() {
        val email: String = email_login.text.toString()
        val password: String = password_login.text.toString()

        //validation
        if (password.isBlank() || email.isBlank()) {
            Toast.makeText(this, "Email password can't be blank.", Toast.LENGTH_SHORT).show()
            return
        }

        //login User
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.e("this", "Login Successful")
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email Password doesn't blank", Toast.LENGTH_SHORT).show()
                }
            }
    }
}