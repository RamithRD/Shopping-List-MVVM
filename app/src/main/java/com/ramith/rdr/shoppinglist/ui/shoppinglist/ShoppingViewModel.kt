package com.ramith.rdr.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem
import com.ramith.rdr.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {

        repository.upsert(item)

    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {

        repository.delete(item)

    }

    //coroutine is not needed because i ts only a read operation therefore handled automatically by room library
    fun getAllShoppingItems() = repository.getAllShoppingItems()

}