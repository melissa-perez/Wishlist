package com.example.wishlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WishItemAdapter(
    val wishes: MutableList<Wish>,
    private val wishOnLongClickListener: OnLongClickListener
) :
    RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {
    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val wishView = inflater.inflate(R.layout.wish_item, parent, false)
        return ViewHolder(wishView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.v("pos", position.toString())

        val wish = wishes[position]
        holder.wishName.text = wish.wishName
        holder.wishUrl.text = wish.wishUrl
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
                wishOnLongClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }
}