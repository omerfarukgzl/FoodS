package com.omtaem.foodapp.domain.presentation.data


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