package io.github.hanjoongcho.okhttp3.recipes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class PostForm {
    private val client = OkHttpClient()

    fun run() {
        val formBody = FormBody.Builder()
                .add("menuGubun", "A")
                .add("srhYear", "2018")
                .add("srhLastYear", "2017")
                .add("gubunCode", "LAND")
                .add("sidoCode", "41")
                .add("gugunCode", "41131")
                .add("dongCode", "4113110400")
                .add("rentAmtType", "3")
                .build()
        val request = Request.Builder()
                .url("http://rt.molit.go.kr/new/gis/getDanjiComboAjax.do")
                .addHeader("Referer", "http://rt.molit.go.kr/")
                .post(formBody)
                .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code " + response)

//            println(response.body()?.string())
            val gson: Gson = GsonBuilder().create()

            response.body()?.let {
                val jsonObject: JsonObject = gson.fromJson(it.string(), JsonObject::class.java)
                println(jsonObject.get("jsonList"))
            }
        }
    }
}

fun main(args: Array<String>) {
    PostForm().run()
}