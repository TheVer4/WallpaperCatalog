package com.example.wallpapercatalog.domain

import androidx.room.TypeConverter

class DataConverter {
    @TypeConverter
    fun fromListToString(list: List<String>): String =
        list.joinToString(separator = "^")

    @TypeConverter
    fun fromStringToList(str: String): List<String> =
        str.split('^')
}