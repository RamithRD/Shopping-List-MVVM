package com.ramith.rdr.shoppinglist.ui.shoppinglist.listeners

import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item : ShoppingItem)

}