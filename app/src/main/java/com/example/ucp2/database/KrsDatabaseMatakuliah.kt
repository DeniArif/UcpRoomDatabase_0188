package com.example.ucp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2.entity.Matakuliah

@Database(entities = [Matakuliah::class], version = 1, exportSchema = false)
abstract class KrsDatabaseMatakuliah : RoomDatabase(){

}