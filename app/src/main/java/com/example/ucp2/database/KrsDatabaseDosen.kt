package com.example.ucp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
annotation class KrsDatabaseDosen : RoomDatabase(){

    abstract fun dosen(): DosenDao
}