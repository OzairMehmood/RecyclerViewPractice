package com.example.recyclerviewpractice.Models

import android.net.Uri

data class Person(
    val name: String,
    val fname: String,
    val province: String,
    val Gender: String,
    val favourite: List<String>,
    val imageurl: Uri?
)