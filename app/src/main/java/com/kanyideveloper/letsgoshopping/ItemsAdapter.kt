package com.kanyideveloper.letsgoshopping

import android.app.Activity
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
import com.squareup.picasso.Picasso


class ItemsAdapter(val context: Context) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val itemList = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shopping_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
           Glide.with(context)
               .load(item.item_image)
               .into(holder.itemImage)
            holder.itemName.text = item.item_name
            holder.itemPrice.text = "Ksh.${item.item_price.toString()}"

        holder.card.setOnClickListener {
            val intent: Intent = Intent(context, ItemDetailsActivity::class.java)


            //intent.putExtra("ITEM_IMAGE",item.item_image)
            intent.putExtra("ITEM_NAME",item.item_name)
            intent.putExtra("ITEM_PRICE",item.item_price.toString())
            intent.putExtra("ITEM_OLD_PRICE",item.old_price.toString())

            context.startActivity(intent)

        }

        }

        override fun getItemCount(): Int {
            return itemList.size
        }

    fun addItemList(list: List<Item>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemName: TextView = view.findViewById(R.id.item_name)
            val card: CardView = view.findViewById(R.id.shopping_card)
        val itemPrice: TextView = view.findViewById(R.id.item_current_price)
    }
}


