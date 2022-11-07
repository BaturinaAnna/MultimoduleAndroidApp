package ru.ozon.route256.core_local_storage_api.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun restoreList(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun restoreMap(mapStringToString: String?): Map<String, String?>? {
        return Gson().fromJson(mapStringToString, object : TypeToken<Map<String, String?>?>() {}.type)
    }

    @TypeConverter
    fun saveMap(mapStringToString: Map<String, String?>?): String? {
        return Gson().toJson(mapStringToString)
    }
}