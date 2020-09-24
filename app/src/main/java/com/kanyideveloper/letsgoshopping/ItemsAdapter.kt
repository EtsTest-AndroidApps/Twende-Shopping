package com.kanyideveloper.letsgoshopping

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ItemsAdapter(private val context: Context, options: FirebaseRecyclerOptions<Item>) :
        FirebaseRecyclerAdapter<Item, ItemsAdapter.ViewHolder>(options) {


    private val itemList = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shopping_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Item) {
        val item = itemList[position]
        Glide.with(context)
                .load(item.itemImage)
                .into(holder.mImage)
        holder.mName.text = item.itemName
        holder.mPrice.text = item.itemPrice.toString()


        holder.card.setOnClickListener {

            val intent = Intent(context, ItemDetailsActivity::class.java)
            intent.putExtra("ITEM_IMAGE",item.itemImage)
            intent.putExtra("ITEM_NAME",item.itemName)
            intent.putExtra("ITEM_PRICE",item.itemPrice.toString())
            intent.putExtra("ITEM_OLD_PRICE",item.itemOldPrice.toString())

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mImage: ImageView = view.findViewById(R.id.item_image)
        val mName: TextView = view.findViewById(R.id.item_name)
        val card: CardView = view.findViewById(R.id.shopping_card)
        val mPrice: TextView = view.findViewById(R.id.item_current_price)
    }
}