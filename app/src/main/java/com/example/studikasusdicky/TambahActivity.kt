package com.example.studikasusdicky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studikasusdicky.databinding.ActivityTambahBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TambahActivity : AppCompatActivity() {
    lateinit var binding: ActivityTambahBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editNominal = binding.nominalPinjaman.text
        val editTenor = binding.tenorBulan.text

        binding.btnSimulasi.setOnClickListener {
            binding.tfPinjaman.setText(editNominal.toString())
            binding.tfTenor.setText(editTenor.toString())
            hitungTenor(editNominal.toString().toInt(), editTenor.toString().toInt())
        }

        binding!!.btnSubmit.setOnClickListener {
            val users = hashMapOf(
                "Pinjaman" to editNominal.toString(),
                "Tenor" to editTenor.toString(),
                "Angsuran" to hitungTenor(editNominal.toString().toInt(), editTenor.toString().toInt()),
            )
            db.collection("kredits")
                .add(users)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }

        }
    }

    fun hitungTenor(nominal:Int, tenor:Int) {
        val hasil = (nominal*0.5) / tenor
        binding.tfAngsuran.setText(hasil.toString())
    }
}