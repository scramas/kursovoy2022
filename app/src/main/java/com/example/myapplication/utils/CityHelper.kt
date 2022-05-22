package com.example.myapplication.utils

import android.content.Context
import android.util.JsonReader
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

object CityHelper {
    //получаем из json файла название региона
    fun  getAllRegions(context:Context):ArrayList<String>{
        var tempArray =ArrayList<String>()
        try {
            val inputStream:InputStream = context.assets.open("Russia.json")
            val size:Int = inputStream.available()
            val byteArray =ByteArray(size)
            inputStream.read(byteArray)
            val jsonFileRegions:String = String(byteArray)
            val jsonObject =JSONObject(jsonFileRegions)
            val regionName= jsonObject.names()
            if (regionName !=null) {
                for (n in 0 until regionName.length()) {
                    tempArray.add(regionName.getString(n))


                }
            }
        }
        catch (e:IOException){

        }
        return tempArray
    }
    //получаем из json файла название города
    fun  getAllCity(region:String,context:Context):ArrayList<String>{
        var tempArray =ArrayList<String>()
        try {
            val inputStream:InputStream = context.assets.open("Russia.json")
            val size:Int = inputStream.available()
            val byteArray =ByteArray(size)
            inputStream.read(byteArray)
            val jsonFileRegions:String = String(byteArray)
            val jsonObject =JSONObject(jsonFileRegions)
            val cityName= jsonObject.getJSONArray(region)

                for (n in 0 until cityName.length()) {
                    tempArray.add(cityName.getString(n))
            }



        }
        catch (e:IOException){

        }
        return tempArray
    }
    //фильтрация
    fun  FilterListData(list: ArrayList<String>, searshText:String?) :ArrayList<String> {
        val tempList = ArrayList<String>()
        tempList.clear()
        if (searshText ==null){
            tempList.add("Нет результата ")
            return  tempList
        }
        for (selection: String in list) {
            if (selection.toLowerCase(Locale.ROOT).startsWith(searshText.toLowerCase(Locale.ROOT)))
                tempList.add(selection)
        }
        if (tempList.size==0)tempList.add("Нет результата")
        return tempList

    }
}