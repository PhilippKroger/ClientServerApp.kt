package com.example.clientserverappkt

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException
import javax.security.auth.callback.Callback


class BaseModel(
    private val jokeService: JokeService,
    private val manageResources: ManageResources
): Model<Joke, Error> {

    private var callback: ResultCallback<Joke, Error>?=null

    private val noConnection by lazy  { Error.NoConnection(manageResources) }
    private val serviceError by lazy { Error.ServiceUnavailable(manageResources) }

    override fun fetch() {
        jokeService.joke().enqueue(object : retrofit2.Callback<JokeCloud> {

            override fun onResponse(call: Call<JokeCloud>, response: Response<JokeCloud>) {
                if (response.isSuccessful) {
                    callback?.provideSuccess(response.body()!!.toJoke())
                } else {
                    callback?.provideError(serviceError)
                }
            }

            override fun onFailure(call: Call<JokeCloud>, t: Throwable) {
                if (t is UnknownHostException || t is java.net.ConnectException) {
                    callback?.provideError(noConnection)
                } else {
                    callback?.provideError(serviceError)
                }
            }

        })
        /*
        jokeService.joke(object : ServerCallback {
            override fun returnSuccess(data: JokeCloud) {
                callback?.provideSuccess(data.toJoke())
            }

            override fun returnError(errorType: ErrorType) {
                when (errorType) {
                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
                    ErrorType.OTHER -> callback?.provideError(serviceError)
                }
            }
        })

         */
    }

    override fun clear() {
        callback = null
    }

    override fun init(resultCallback: ResultCallback<Joke, Error>) {
        callback = resultCallback
    }
}