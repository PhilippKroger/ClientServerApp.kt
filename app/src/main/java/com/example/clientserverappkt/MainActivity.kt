package com.example.clientserverappkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.clientserverappkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as JokeApp).viewModel

        binding.actionButton.setOnClickListener {
            binding.actionButton.isEnabled = false
            viewModel.getJoke()
        }

        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread{
                binding.actionButton.isEnabled = true
                binding.textView.text=text
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}