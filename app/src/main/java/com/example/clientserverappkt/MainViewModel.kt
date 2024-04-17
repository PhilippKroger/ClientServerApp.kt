package com.example.clientserverappkt

import androidx.lifecycle.ViewModel
import org.w3c.dom.Text

class MainViewModel(private val model: Model<Joke, Error>) {
    private var textCallback: TextCallback =TextCallback.Empty()

    fun getJoke() {
        model.fetch()
    }

    fun init(textCallback: TextCallback) {
        this.textCallback = textCallback
        model.init(object : ResultCallback<Joke, Error> {
            override fun provideSuccess(data: Joke) {
                textCallback.provideText(data.toUi())
            }

            override fun provideError(error: Error) {
                textCallback.provideText(error.message())
            }

        })
    }

    fun clear() {
        textCallback = TextCallback.Empty()
        model.clear()
    }
}

interface TextCallback {
    fun provideText(text: String)

    // Смарт-контракт класс пустышка
    class Empty : TextCallback {
        override fun provideText(text: String) = Unit
    }
}