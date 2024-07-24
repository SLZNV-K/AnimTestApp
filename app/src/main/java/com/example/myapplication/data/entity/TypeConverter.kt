package com.example.myapplication.data.entity

import androidx.room.TypeConverter
import com.example.myapplication.domain.dto.Edge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NodeTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun nodeToJson(node: NodeEntity?): String? {
        return gson.toJson(node)
    }

    @TypeConverter
    fun jsonToNode(json: String?): NodeEntity? {
        return gson.fromJson(json, NodeEntity::class.java)
    }

    @TypeConverter
    fun choiceListToJson(choices: List<Edge>?): String? {
        return gson.toJson(choices)
    }

    @TypeConverter
    fun jsonToList(json: String?): List<Edge>? {
        return gson.fromJson(json, object : TypeToken<List<Edge>>() {}.type)
    }
}