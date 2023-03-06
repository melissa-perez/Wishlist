package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WishItemAdapter(
    private val wishes: MutableList<Wish>,
    private val wishListeners: ClickListeners,
) :
    RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {
    interface ClickListeners {
        fun onItemLongClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val wishView = inflater.inflate(R.layout.wish_item, parent, false)
        return ViewHolder(wishView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = wishes[position]
        val s = SpannableStringBuilder(wish.wishUrl)
        s.setSpan(UnderlineSpan(), 0, wish.wishUrl.length, 0)
        holder.wishName.text = wish.wishName
        holder.wishUrl.text = s
        holder.wishPrice.text = String.format("%.2f", wish.wishPrice)
    }

    override fun getItemCount(): Int {
        return wishes.size
    }

    inner class ViewHolder(wishView: View) : RecyclerView.ViewHolder(wishView) {
        val wishName: TextView
        val wishUrl: TextView
        val wishPrice: TextView

        init {
            wishName = wishView.findViewById(R.id.wishItemView)
            wishUrl = wishView.findViewById(R.id.wishUrlView)
            wishPrice = wishView.findViewById(R.id.wishPriceView)
            wishView.setOnLongClickListener {
                wishListeners.onItemLongClicked(adapterPosition)
                true
            }
            wishUrl.setOnClickListener {
                val item = wishUrl.text.toString()
                try {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item))
                    ContextCompat.startActivity(it.context, browserIntent, null)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(it.context, "Invalid URL for $item", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}