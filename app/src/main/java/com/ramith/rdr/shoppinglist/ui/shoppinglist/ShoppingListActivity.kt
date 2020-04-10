package com.ramith.rdr.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramith.rdr.shoppinglist.R
import com.ramith.rdr.shoppinglist.data.adapaters.ShoppingItemAdapter
import com.ramith.rdr.shoppinglist.data.db.entities.ShoppingItem
import com.ramith.rdr.shoppinglist.ui.shoppinglist.dialogs.AddShoppingItemDialog
import com.ramith.rdr.shoppinglist.ui.shoppinglist.listeners.AddDialogListener
import kotlinx.android.synthetic.main.activity_shopping_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingListActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        val viewModel = ViewModelProvider(
            this@ShoppingListActivity,
            factory
        ).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {

            //automatically updates data from db to recycler-view whenever a change happens through LiveData library
            adapter.shoppingItems = it
            adapter.notifyDataSetChanged()

        })


        //add new item to db
        fab.setOnClickListener {

            AddShoppingItemDialog(this,

                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()

        }

    }
}
