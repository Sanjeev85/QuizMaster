package com.example.quizmaster.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class signUp_activity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()

        signup.setOnClickListener {
            signUpUser()
        }

        redirect_login.setOnClickListener {
            val intent = Intent(applicationContext, login_activity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun signUpUser() {
        val email: String = email.text.toString()
        val password: String = password.text.toString()
        val confirm_pass: String = confirm_password.text.toString()

        //validation
        if (password.isBlank() || email.isBlank() || confirm_pass.isBlank()) {
            Toast.makeText(this, "Email password can't be blank.", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirm_pass) {
            Log.e("this", "Confirm password didn't matches")
            Toast.makeText(this, "Confirm password didn't matches.", Toast.LENGTH_SHORT).show()
            return
        }

        //create user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.e("this", "User created")

                } else {
                    Log.e("this", "Error creating user")
                    Toast.makeText(this, "Error Creating User.", Toast.LENGTH_SHORT).show()
                }
            }


    }


}