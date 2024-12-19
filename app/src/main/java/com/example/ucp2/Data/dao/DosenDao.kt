package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.Data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    @Query("SELECT * from dosen ORDER BY nama ASC")
    fun getAllDosen(): Flow<List<Dosen>>

    @Query("SELECT * FROM Dosen WHERE nidn = nidn = :nidn")
    fun getDosen(nidn: String): Flow<Dosen>

    @Insert
    suspend fun insertDosen(dosen: Dosen)
}

