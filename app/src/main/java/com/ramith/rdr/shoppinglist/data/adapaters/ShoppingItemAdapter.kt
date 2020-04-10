package com.ramith.rdr.shoppinglist.data.adapaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramith.rdr.shoppinglist.R
import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem
import com.ramith.rdr.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(var shoppingItems : List<ShoppingItem>, private val viewModel : ShoppingViewModel) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val currentShoppingItem = shoppingItems.get(position)

        holder.itemView.tvName.text = currentShoppingItem.name
        holder.itemView.tvAmount.text = currentShoppingItem.amount.toString()

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {

            if(currentShoppingItem.amount > 0){

                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)

            }

        }

    }

    //viewholder impl for recycler view adapter
    inner class ShoppingViewHolder(imageView : View) : RecyclerView.ViewHolder(imageView)  {

    }

}