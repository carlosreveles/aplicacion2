package com.example.myfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestoreSettings


class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val dataTextView: TextView = findViewById(R.id.datatextView)

        val db = FirebaseFirestore.getInstance()

        val collectionPath = "platillos"

        db.collection(collectionPath).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot: QuerySnapshot? = task.result
                    if (querySnapshot != null) {
                        val stringBuilder = StringBuilder()
                        for (document in querySnapshot.documents) {
                            // Accede a los campos de cada documento
                            val campo1 = document.getString("Nombre")
                            val campo2 = document.getString("Descripcion")
                            val campo3 = document.getString("Precio")
                            val campo4 = document.getString("Size")
                            val campo5 = document.getString("Imagen")
                            // Agrega los datos al StringBuilder
                            stringBuilder.append("Nombre: $campo1\n\n")
                            stringBuilder.append("Descripcion: $campo2\n\n")
                            stringBuilder.append("Precio: $campo3\n\n")
                            stringBuilder.append("Size: $campo4\n\n")
                            stringBuilder.append("Imagen: $campo5\n\n")
                        }
                        // Muestra los datos en el TextView
                        dataTextView.text = stringBuilder.toString()
                    }
                } else {
                    Log.e(TAG, "Error al obtener los datos: ", task.exception)
                }
    }
}
}