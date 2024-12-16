package com.example.ucp2.Data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.Data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface MataKuliahDao {
    @Query("select * From MataKuliah ORDER BY nama ASC")
    fun getAllMataKuliah() : Flow<List<MataKuliah>>

    @Query("SELECT * FROM MataKuliah WHERE kode = kode = :kode")
    fun getMataKuliah(kode: String): Flow<MataKuliah>

    @Delete
    suspend fun deleteMataKuliah(MataKuliah: MataKuliah)

    @Update
    suspend fun updateMataKuliah(MataKuliah: MataKuliah)

    @Insert
    suspend fun insertMataKuliah(MataKuliah: MataKuliah)
}