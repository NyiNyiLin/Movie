package com.nyi.movie.feature.scroll

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyi.movie.R

import kotlinx.android.synthetic.main.activity_main2.*
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.flexibleadapter.databinding.BindingAdapters.setAdapter
import eu.davidea.flexibleadapter.FlexibleAdapter
import kotlinx.android.synthetic.main.activity_main_1.*
import kotlinx.android.synthetic.main.content_main2.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Optional but strongly recommended: Compose the initial list
        val myItems = getDatabaseList()

        // Initialize the Adapter
        val adapter = FlexibleAdapter<IFlexible<*>>(myItems)


        adapter.setAnimationOnForwardScrolling(true)
        adapter.setAnimationOnReverseScrolling(true)

        // Initialize the RecyclerView and attach the Adapter to it as usual
        rv_activity_main_2.adapter = adapter
        rv_activity_main_2.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        // Enable long press drag
        adapter.setLongPressDragEnabled(true);

        // Enable handle drag (needs an inner view in ViewHolder)
        adapter.setHandleDragEnabled(true);

        // Enable Swipe-To-Dismiss (needs views setup in ViewHolder)
        adapter.setSwipeEnabled(true)

    }

    fun getDatabaseList(): List<IFlexible<*>> {
        val list = ArrayList<MyItem>()
        list.add(MyItem("1", "Hello"))
        list.add(MyItem("2", "World"))
        list.add(MyItem("1", "Hello"))
        list.add(MyItem("2", "World"))

        list.add(MyItem("1", "Hello"))
        list.add(MyItem("2", "World"))
        list.add(MyItem("1", "Hello"))
        list.add(MyItem("2", "World"))
        list.add(MyItem("1", "Hello"))
        list.add(MyItem("2", "World"))
        return list
    }

}
