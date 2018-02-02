package io.github.hanjoongcho.okhttp3.recipes.postform

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class PostFormB {
    private val client = OkHttpClient()

    fun run() {
        val formBody = FormBody.Builder()
                .add("reqPage", "SRH")
                .add("menuGubun", "A")
                .add("srhType", "LOC")
                .add("houseType", "1")
                .add("srhYear", "2017")
                .add("srhPeriod", "4")
                .add("gubunCode", "LAND")
                .add("sidoCode", "41")
                .add("gugunCode", "41131")
                .add("dongCode", "4113110400")
                .add("rentAmtType", "3")
                .build()
        val request = Request.Builder()
                .url("http://rt.molit.go.kr/srh/getListAjax.do")
                .addHeader("Referer", "http://rt.molit.go.kr/")
                .post(formBody)
                .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code " + response)

//            println(response.body()?.string())
            val gson: Gson = GsonBuilder().create()
            val prettyPrinter = GsonBuilder().setPrettyPrinting().create()
            response.body()?.let {
                val jsonObject: JsonObject = gson.fromJson(it.string(), JsonObject::class.java)
                val jsonArray: JsonArray = jsonObject.getAsJsonArray("jsonList")
                println(jsonObject)
                jsonArray.map {
                    val item: JsonObject = it.asJsonObject
//                    println(prettyPrinter.toJson(item))
//                    item.getAsJsonArray("month1List").map { monthItem ->
//                        println(prettyPrinter.toJson(monthItem))
//                    }
//                    item.getAsJsonArray("month2List").map { monthItem ->
//                        println(prettyPrinter.toJson(monthItem))
//                    }
//                    item.getAsJsonArray("month3List").map { monthItem ->
//                        println(prettyPrinter.toJson(monthItem))
//                    }
//                    println("\n")
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    PostFormB().run()
}