package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Float.parseFloat


class MainActivity : AppCompatActivity() {
    lateinit var listOfWishes: MutableList<Wish>
    lateinit var wishAdapter: WishItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishClickListeners = object : WishItemAdapter.ClickListeners {
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
        wishAdapter = WishItemAdapter(listOfWishes, wishClickListeners)
        wishesRV.adapter = wishAdapter
        wishesRV.layoutManager = LinearLayoutManager(this)


        findViewById<Button>(R.id.wishSubmitBtn).setOnClickListener {
            val userInputWish = wishInput.text.toString().trim()
            val userInputWishPrice: Float
            val userInputWishUrl = wishUrlInput.text.toString().trim()

            if (userInputWish.isEmpty()) {
                Toast.makeText(it.context, "Please enter an wish item name.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (wishPriceInput.text.toString().isEmpty()) {
                Toast.makeText(it.context, "Please enter a price.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else
                userInputWishPrice = parseFloat(wishPriceInput.text.toString())

            listOfWishes.add(Wish(userInputWish, userInputWishUrl, userInputWishPrice))


            wishAdapter.notifyItemInserted(listOfWishes.size - 1)
            wishInput.text.clear()
            wishPriceInput.text.clear()
            wishUrlInput.text.clear()

            wishInput.onEditorAction(EditorInfo.IME_ACTION_DONE)
            wishPriceInput.onEditorAction(EditorInfo.IME_ACTION_DONE)
            wishUrlInput.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }
    }

}