package com.my.first.appdenotas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.my.first.appdenotas.databinding.ActivityAgregarNotaBinding

class AgregarNotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarNotaBinding
    private lateinit var db : NotasDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgregarNotaBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)


        db = NotasDatabaseHelper(this)

        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val description = binding.etDescripcion.text.toString()

            if (!titulo.isEmpty() && !description.isEmpty()){
                guardarNota(titulo, description)
            }else {
                Toast.makeText(applicationContext, "llene los campos" , Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun guardarNota(titulo : String , description : String){
        val nota = Nota(0, titulo , description)
        db.inserNota(nota)
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finishAffinity()
        Toast.makeText(applicationContext, "Se ah agregado la nota" , Toast.LENGTH_SHORT).show()
    }
}