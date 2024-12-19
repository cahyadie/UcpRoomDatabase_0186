package com.example.ucp2.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.Data.dao.DosenDao
import com.example.ucp2.Data.dao.MataKuliahDao
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.MataKuliah

@Database(entities = [Dosen::class, MataKuliah::class],version = 1, exportSchema = false )
abstract class DatabaseAll : RoomDatabase() {

    abstract fun DosenDao(): DosenDao
    abstract fun MataKuliahDao(): MataKuliahDao

    companion object {
        @Volatile
        private var Instance: DatabaseAll? = null

        fun getDatabase(context: Context): DatabaseAll {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseAll::class.java,
                    "DatabaseAll"
                )
                    .build().also { Instance = it }
            })
        }
    }
}