package com.example.recyclerviewpractice

import android.R
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewpractice.Models.Person
import com.example.recyclerviewpractice.MySharedData.personList
import com.example.recyclerviewpractice.MySharedData.provinceData
import com.example.recyclerviewpractice.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var imageuri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.provincess.adapter =
            ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, provinceData)
        binding.btnNext.setOnClickListener {
            val nextActivity = Intent(this, RecyclerViewActivity::class.java)
            startActivity(nextActivity)
        }
        binding.btnimg.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        binding.btnadd.setOnClickListener {
            val name = binding.nameEt.text.toString().trim()
            val father = binding.fnameEt.text.toString().trim()
            val province = binding.provincess.selectedItem.toString()
            var gender = ""
            val isMale = binding.rbMale.isChecked
            if (isMale) {
                gender = "Male"
            } else gender = "Female"
            // Collect favourites
            val favouriteList = mutableListOf<String>()
            if (binding.chkandroid.isChecked) favouriteList.add("Android")
            if (binding.chkkotlin.isChecked) favouriteList.add("Kotlin")
            if (binding.chkjava.isChecked) favouriteList.add("Java")

            // Create person object
            val person = Person(name, father, province, gender, favouriteList, imageuri)

            // Add to global list
            personList.add(person)

            Toast.makeText(this, "Person Added!", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageuri = data?.data
        binding.btnimg.setImageURI(imageuri)
    }
}

object MySharedData {
    var provinceData = listOf<String>(
        "Punjab",
        "Khyber Pakhtun Khuwa",
        "Sindh",
        "Balochistan",
        "Kashmir",
        "Gilgit"
    )
    val personList = mutableListOf<Person>()
}