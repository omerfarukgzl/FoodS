package com.omtaem.foodapp.domain.presentation.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class RecipesTypeConverter {
    var gson = Gson()
    @TypeConverter
    fun resultToString(result:Result): String{
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result{
        val listType = object : TypeToken<Result>(){}.type
        return gson.fromJson(data,listType)
    }
}