package io.github.hanjoongcho.okhttp3.recipes.postform

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class PostFormA {
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
                val jsonArray: JsonArray = jsonObject.getAsJsonArray("jsonList")
                jsonArray.map {
//                    {"ROAD_BUILDBUN2":"00000","EUB":"10400","V_Y":37.46231704453,"ROAD_BUILDBUN1":"00024","REG":"41131","ROAD_NAME":"수정로466번길","JIBUN_NAME":"경기도 성남수정구  단대동","V_X":127.158469280074,"BUN1":"0003","BUN2":"0000","APT_NAME":"낙원","APT_CODE":6176}
                    val item: JsonObject = it.asJsonObject
                    println("${item.get("JIBUN_NAME")} ${item.get("V_X")} ${item.get("V_Y")} ${item.get("APT_NAME")}")
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    PostFormA().run()
}