package com.example.day16database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val email = findViewById<TextInputEditText>(R.id.etMail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val userId = findViewById<TextInputEditText>(R.id.uniqueId)

        signButton.setOnClickListener{
            val name = etName.text.toString()
            val mail = email.text.toString()
            val password = etPassword.text.toString()
            val uniqueId = userId.text.toString()

            val user = Users(name, mail, password , uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                email.text?.clear()
                etPassword.text?.clear()
                userId.text?.clear()
                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Failed!!", Toast.LENGTH_SHORT).show()
            }
        }
         val signIntext = findViewById<TextView>(R.id.tvSignIn)
        signIntext.setOnClickListener{
            val openSignInActivity = Intent(this,SignIn::class.java)
            startActivity(openSignInActivity)
        }
    }
}