package com.example.dictionary.feature_dictionary.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(private val gson:Gson):JsonParser {
    override fun <T> fromJson(json: String, type: Type): T? {
        TODO("Not yet implemented")
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        TODO("Not yet implemented")
    }
}