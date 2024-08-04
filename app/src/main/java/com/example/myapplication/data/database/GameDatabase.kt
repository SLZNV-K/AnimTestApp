package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.NodeDao
import com.example.myapplication.data.dao.TranslationsEdgeMessageDao
import com.example.myapplication.data.dao.TranslationsNodeMessageDao
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.data.entity.NodeTypeConverter
import com.example.myapplication.data.entity.TranslationNodeMessageEntity
import com.example.myapplication.data.entity.TranslationsEdgeMessageEntity

@Database(
    entities = [NodeEntity::class, TranslationNodeMessageEntity::class, TranslationsEdgeMessageEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(NodeTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): NodeDao
    abstract fun translationsNodeMessageDao(): TranslationsNodeMessageDao
    abstract fun translationsEdgeMessageDao(): TranslationsEdgeMessageDao
}