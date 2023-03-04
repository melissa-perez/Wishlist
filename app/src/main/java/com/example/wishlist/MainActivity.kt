package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val wishes: List<Wish> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishesRV: RecyclerView = findViewById(R.id.wishList)
        val wishAdapter = WishItemAdapter(wishes)
        wishesRV.adapter = wishAdapter
        wishesRV.layoutManager = LinearLayoutManager(this)
    }
}