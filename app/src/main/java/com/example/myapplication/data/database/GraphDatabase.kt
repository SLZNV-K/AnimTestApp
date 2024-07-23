package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.GraphDao
import com.example.myapplication.data.entity.EdgesDao
import com.example.myapplication.data.entity.NodeDao
import com.example.myapplication.data.entity.NodeTypeConverter

@Database(entities = [NodeDao::class, EdgesDao::class], version = 1)
@TypeConverters(NodeTypeConverter::class)
abstract class GraphDatabase : RoomDatabase() {
    abstract fun graphDao(): GraphDao
}