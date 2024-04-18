package com.example.clientserverappkt

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

interface JokeService {

    @GET("random_joke")
    fun joke() : Call<JokeCloud>




    /*
    class Base(
        private val gson: Gson
    ): JokeService{
        override fun joke(callback: ServerCallback) {
            Thread {
                var connection: HttpURLConnection? = null
                try {
                    val url = URL(URL)
                    connection = url.openConnection() as HttpURLConnection
                    InputStreamReader(BufferedInputStream(connection.inputStream)).use {
                        val text = it.readText()
                        callback.returnSuccess(gson.fromJson(text, JokeCloud::class.java))
                    }
                } catch (e: Exception) {
                    if (e is UnknownHostException || e is java.net.ConnectException) {
                        callback.returnError(ErrorType.NO_CONNECTION)
                    } else {
                        callback.returnError(ErrorType.OTHER)
                    }
                } finally {
                    connection?.disconnect()
                }
            }.start()
        }

     */

        companion object {
            private const val URL = "https://official-joke-api.appspot.com/random_joke/"
        }


}

interface ServerCallback {
    fun returnSuccess(data: JokeCloud)
    fun returnError(errorType: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}