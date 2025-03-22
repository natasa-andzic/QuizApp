package com.natasa.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.natasa.quizapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var signUpBinding: ActivitySignUpBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        signUpBinding.signUpBtn.setOnClickListener {
            val email = signUpBinding.signUpEmailEt.text.toString()
            val password = signUpBinding.signUpPasswordEt.text.toString()

            signUpWithFirebase(email, password)
        }

    }

    fun signUpWithFirebase(email: String, password: String) {
        signUpBinding.progressBar.visibility = View.VISIBLE
        signUpBinding.signUpBtn.isClickable = false

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                signUpBinding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(
                    applicationContext,
                    "Your account has been created",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
                signUpBinding.signUpBtn.isClickable = true

            } else {
                Toast.makeText(
                    applicationContext,
                    task.exception?.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }

}