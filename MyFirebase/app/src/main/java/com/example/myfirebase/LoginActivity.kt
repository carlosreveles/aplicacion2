package com.example.myfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var editTextLogin: EditText
    lateinit var editPassLogin: EditText
    //lateinit var editConLogin: EditText
    lateinit var btnLogin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextLogin = findViewById(R.id.emailLogin)
        editPassLogin = findViewById(R.id.passLogin)
        // editConLogin = findViewById(R.id.pasconfLogin)
        btnLogin = findViewById(R.id.loginl)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            login()
        }

    }

    private fun login(){
        val email = editTextLogin.text.toString()
        val pass = editPassLogin.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Login exitoso",Toast.LENGTH_LONG).show()
                val intent = Intent(this, DataActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Verifica los datos",Toast.LENGTH_LONG).show()
            }
        }
    }
}
