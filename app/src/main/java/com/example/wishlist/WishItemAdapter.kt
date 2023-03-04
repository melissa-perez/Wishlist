package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishItemAdapter(private val wishes: List<Wish>) :
    RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {

    class ViewHolder(wishView: View) : RecyclerView.ViewHolder(wishView) {
        val wishName: TextView
        val wishUrl: TextView
        val wishPrice: TextView

        init {
            wishName = wishView.findViewById(R.id.wishItemView)
            wishUrl = wishView.findViewById(R.id.wishUrlView)
            wishPrice = wishView.findViewById(R.id.wishItemPrice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val wishView = inflater.inflate(R.layout.wish_item, parent, false)
        return ViewHolder(wishView)
    }

    override fun getItemCount(): Int {
        return wishes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = wishes[position]
        holder.wishName.text = wish.wishName
        holder.wishUrl.text = wish.wishUrl
        holder.wishPrice.text = wish.wishPrice.toString()
    }
}