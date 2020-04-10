package com.ramith.rdr.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem

//define the entities related to this database by class and add them to the entities array
@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao() : ShoppingDao

    //create Singleton for the DB class
    companion object {

        //mark as volatile to make it thread safe and to prevent multiple instances of DB class being created
        @Volatile
        private var instance : ShoppingDatabase? = null
        private val LOCK = Any()

        //called automatically when an instance of ShoppingDatabase is created
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            instance
                ?: createDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun createDatabase(context : Context) = Room.databaseBuilder(context.applicationContext,
                                                                                    ShoppingDatabase::class.java,"ShopinngDB.db").build()

    }

}