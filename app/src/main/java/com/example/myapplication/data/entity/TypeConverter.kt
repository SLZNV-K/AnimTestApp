package com.example.myapplication.data.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NodeTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun nodeToJson(node: NodeDao?): String? {
        return gson.toJson(node)
    }

    @TypeConverter
    fun jsonToNode(json: String?): NodeDao? {
        return gson.fromJson(json, NodeDao::class.java)
    }

    @TypeConverter
    fun choiceListToJson(choices: List<EdgesDao>?): String? {
        return gson.toJson(choices)
    }

    @TypeConverter
    fun jsonToChoiceList(json: String?): List<EdgesDao>? {
        return gson.fromJson(json, object : TypeToken<List<EdgesDao>>() {}.type)
    }
}