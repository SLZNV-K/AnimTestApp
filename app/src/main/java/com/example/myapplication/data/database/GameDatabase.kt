package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.GameDao
import com.example.myapplication.data.entity.NodeEntity
import com.example.myapplication.data.entity.NodeTypeConverter

@Database(entities = [NodeEntity::class], version = 5, exportSchema = false)
@TypeConverters(NodeTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}