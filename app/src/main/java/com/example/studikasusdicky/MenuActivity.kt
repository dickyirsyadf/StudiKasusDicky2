package com.example.studikasusdicky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studikasusdicky.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTambah.setOnClickListener {
            val intent = Intent(this, TambahActivity::class.java)
            startActivity(intent)
        }
    }

}