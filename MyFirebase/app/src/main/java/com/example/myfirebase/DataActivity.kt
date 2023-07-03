package com.example.myfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.myfirebase.databinding.ActivityDataBinding
import com.google.firebase.firestore.FirebaseFirestore

const val TAG = "FIRESTORE"

class DataActivity : AppCompatActivity() {

    private var binding: ActivityDataBinding? = null
    //private lateinit var btndata: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_data)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        uploadData()
        //readData()

        val button = findViewById<Button>(R.id.btnReadData)
        button.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

    }

    class fireStoreUtils {
        val firestoreDatabase = FirebaseFirestore.getInstance()
    }

    private fun uploadData() {
        binding!!.btnUploadData.setOnClickListener {
            val hashMap = hashMapOf<String, Any>(
                "Descripcion" to "Papas bien fritas con salsa",
                "Nombre" to "Papas Fritas",
                "Precio" to "$100",
                "Size" to "Grandes",
                "Imagen" to "gs://myfirebase-40e43.appspot.com/Papas.jpg"
            )
            fireStoreUtils().firestoreDatabase.collection("platillos")
                .add(hashMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Se añadio el documento con un ID {${it.id}}")
                    Toast.makeText(
                        this,
                        "Se ha creado un nuevo documento con ID {$it.id}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener {
                    Log.d(TAG, "El documento no se pudo agregar")
                }
        }
    }

   /* private fun readData() {
        binding!!.btnReadData.setOnClickListener {
            fireStoreUtils().firestoreDatabase.collection("platillos")
                .get()
                .addOnSuccessListener { QuerySnapshot ->
                    QuerySnapshot.forEach { Document ->
                        Log.d(TAG, "Documento recuperado con ID: {${Document.id}}")
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Hubo un error para recuperar la informacion", Toast.LENGTH_LONG).show()
                }
        }
    }*/
}

