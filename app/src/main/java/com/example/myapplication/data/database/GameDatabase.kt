package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.NodeDao
import com.example.myapplication.data.dao.TranslationDao
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.data.entity.NodeTypeConverter
import com.example.myapplication.data.entity.TranslationNodeMessageEntity

@Database(
    entities = [NodeEntity::class, TranslationNodeMessageEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(NodeTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): NodeDao
    abstract fun translationDao(): TranslationDao
}