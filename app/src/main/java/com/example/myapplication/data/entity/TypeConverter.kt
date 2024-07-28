package com.example.myapplication.data.entity

import androidx.room.TypeConverter
import com.example.myapplication.domain.dto.Edge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NodeTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun edgeListToJson(edges: List<Edge>?): String? {
        if (edges.isNullOrEmpty()) {
            return null
        }
        return gson.toJson(edges)
    }

    @TypeConverter
    fun jsonToListEdge(json: String?): List<Edge>? {
        if (json.isNullOrEmpty()) {
            return emptyList()
        }
        return gson.fromJson(json, object : TypeToken<List<Edge>>() {}.type)
    }
}