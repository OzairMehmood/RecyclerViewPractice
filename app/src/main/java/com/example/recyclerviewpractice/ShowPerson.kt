package com.example.recyclerviewpractice

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewpractice.databinding.ActivityShowPersonBinding

class ShowPerson : AppCompatActivity() {

    private val binding: ActivityShowPersonBinding by lazy {
        ActivityShowPersonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val index = intent.getIntExtra("Index", -1)
        binding.tvIndex.text = "Index $index clicked"
        val name = intent.getStringExtra("Name")
        binding.tvname.text = name
        val imageuri = intent.getStringExtra("Image")?.toUri()
        if(imageuri==null) Toast.makeText(this,"Uri null",Toast.LENGTH_LONG).show()
        binding.img.setImageURI(imageuri)

    }
}