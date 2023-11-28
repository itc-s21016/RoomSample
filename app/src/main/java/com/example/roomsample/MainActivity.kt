package com.example.roomsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.UiThread
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels{
        MyViewModelFactory((application as MyApplication).repository)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allEntities.observe(this, Observer { entities ->
            entities?.let { adapter.submitList(it) }
        })

        binding.saveButton.setOnClickListener @UiThread {
            val inputText = binding.editText.text.toString()
            if (inputText.isNotEmpty()) {
                // データベースに文字列を挿入
                viewModel.insertt(MyEntity(name = inputText))
                // 入力欄をクリア
                binding.editText.text.clear()
            }
        }
        binding.recyclerView.adapter = MyAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
    }
}