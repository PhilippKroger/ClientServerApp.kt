package com.example.clientserverappkt

import android.content.Context
import androidx.annotation.StringRes


// Этот интерфейс предоставляет доступ к ресурсам приложения, таким как строки
interface ManageResources {
    fun string(@StringRes resourceId: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(resourceId: Int): String {
            return context.getString(resourceId)
        }

    }
}