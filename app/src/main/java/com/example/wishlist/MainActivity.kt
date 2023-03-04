package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Float.parseFloat

class MainActivity : AppCompatActivity() {
    lateinit var listOfWishes: MutableList<Wish>
    lateinit var wishAdapter:WishItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishOnLongClickListener = object : WishItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                listOfWishes.removeAt(position)
                wishAdapter.notifyItemRemoved(position)
            }
        }
        listOfWishes = mutableListOf()

        val wishInput = findViewById<EditText>(R.id.editWish)
        val wishPriceInput = findViewById<EditText>(R.id.editWishPrice)
        val wishUrlInput = findViewById<EditText>(R.id.editWishUrl)

        val wishesRV: RecyclerView = findViewById(R.id.wishList)
        wishAdapter = WishItemAdapter(listOfWishes, wishOnLongClickListener)
        wishesRV.adapter = wishAdapter
        wishesRV.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.wishSubmitBtn).setOnClickListener {
            val userInputWish = wishInput.text.toString()
            val userInputWishPrice = parseFloat(wishPriceInput.text.toString())
            val userInputWishUrl = wishUrlInput.text.toString()
            Log.v("user", userInputWish)
            Log.v("user", userInputWishPrice.toString())
            Log.v("user", userInputWishUrl)

            if (listOfWishes.add(Wish(userInputWish, userInputWishUrl, userInputWishPrice)))
                Log.v("user", "hellooooo i entered")
            Log.v("user", listOfWishes[listOfWishes.size - 1].toString())
            wishAdapter.notifyItemInserted(listOfWishes.size - 1)
            wishInput.setText("")
            wishPriceInput.setText("")
            wishUrlInput.setText("")
        }
    }
}