package com.example.foodrecipes.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DishTypesConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDishTypesList(dishTypes: List<String?>?): String? {
        return dishTypes?.let {
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return gson.toJson(dishTypes, type)
        }
    }

    @TypeConverter
    fun toDishTypesList(dishTypesString: String?): List<String>? {
        return dishTypesString?.let {
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return gson.fromJson<List<String>>(dishTypesString, type)
        }
    }
}