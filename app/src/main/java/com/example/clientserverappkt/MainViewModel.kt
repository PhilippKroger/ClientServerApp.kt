package com.example.clientserverappkt

import androidx.lifecycle.ViewModel
import org.w3c.dom.Text

class MainViewModel(private val model: Model<Any, Any>) {
    private var textCallback: TextCallback =TextCallback.Empty()

    fun getJoke() {
        model.fetch()
    }

    fun init(textCallback: TextCallback) {
        this.textCallback = textCallback
        model.init(object : ResultCallback<Any, Any> {
            override fun provideSuccess(data: Any) {
                textCallback.provideText("success")
            }

            override fun provideError(error: Any) {
                textCallback.provideText("error")
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