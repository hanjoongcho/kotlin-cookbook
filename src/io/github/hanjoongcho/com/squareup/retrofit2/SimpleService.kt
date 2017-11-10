package io.github.hanjoongcho.com.squareup.retrofit2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class SimpleService {
    companion object {
        val API_URL = "https://api.github.com"
    }
}


data class Contributor(val login: String, val contributions: Int)

data class Project(val language: String)

data class User(
        val login: String,
        val type: String,
        val name: String,
        val bio: String
)

interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String): Call<List<Contributor>>

    @GET("/repos/{owner}/{repo}")
    fun repository(
            @Path("owner") owner: String,
            @Path("repo") repo: String): Call<Project>

    @GET("/users/{owner}")
    fun user(@Path("owner") owner: String): Call<User>
}

fun getFirstContributorLoginValue(owner: String, repo: String): String {
    // Create a very simple REST adapter which points the GitHub API.
    val retrofit = Retrofit.Builder()
            .baseUrl(SimpleService.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Create an instance of our GitHub API interface.
    val github = retrofit.create(GitHub::class.java)

    // Create a call instance for looking up Retrofit contributors.
    val call = github.contributors(owner, repo)

    // Fetch and print a list of the contributors to the library.
    val contributors = call.execute().body()
    return contributors?.first()?.login ?: ""
}

fun getLanguage(owner: String, repo: String): String {
    val retrofit = Retrofit.Builder()
            .baseUrl(SimpleService.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val github = retrofit.create(GitHub::class.java)
    val call = github.repository(owner, repo)
    val contributors = call.execute().body()
    return contributors?.language ?: ""
}

fun getUserInfo(owner: String): String {
    val retrofit = Retrofit.Builder()
            .baseUrl(SimpleService.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val github = retrofit.create(GitHub::class.java)
    val call = github.user(owner)
    val user = call.execute().body()
    return user.toString()
}