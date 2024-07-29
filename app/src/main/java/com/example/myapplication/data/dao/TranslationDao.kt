package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.entity.TranslationNodeMessageEntity

@Dao
interface TranslationDao {
    @Query("SELECT * FROM translation")
    fun getAllTranslations(): LiveData<List<TranslationNodeMessageEntity>>
}