package com.example.ucp2.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dosen")
data class Dosen (
    @PrimaryKey
    val Nidn: String,
    val nama: String,
    val jeniskelamin: String

)