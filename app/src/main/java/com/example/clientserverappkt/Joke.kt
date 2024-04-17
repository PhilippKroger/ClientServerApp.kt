package com.example.clientserverappkt

class Joke(
    private val text: String,
    private val punchline: String
) {
    fun toUi() = "$text\n$punchline"
}