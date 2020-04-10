package com.ramith.rdr.shoppinglist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//define the data class as a table entity with the table name for Room db
@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_name") //defines the name of the column in the table
    var name : String,
    @ColumnInfo(name = "item_amount")
    var amount : Int
) {

    //define primary key - autogenerated id
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null

}