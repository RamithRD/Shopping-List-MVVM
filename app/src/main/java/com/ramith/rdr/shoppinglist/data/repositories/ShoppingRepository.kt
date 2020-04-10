package com.ramith.rdr.shoppinglist.data.repositories

import com.ramith.rdr.shoppinglist.data.db.ShoppingDatabase
import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem

//the repository class is in charge of retrieving/inserting/manipulating data from local db or webservice
class ShoppingRepository(
    private val db : ShoppingDatabase
) {

    suspend fun upsert(item : ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item : ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()

}