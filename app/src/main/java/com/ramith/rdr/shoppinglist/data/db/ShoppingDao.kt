package com.ramith.rdr.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem

//defines the interface as an DAO (Data Access Object) for Roomdb
@Dao
interface ShoppingDao {

    //note : all DB operation functions are marked as suspend so that we can call them only within a coroutine
    // because calling them on main thread will result in a block

    //used for both table insert and update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item : ShoppingItem)

    @Delete
    suspend fun delete(item : ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>

}