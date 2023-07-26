package com.example.messaging_ui

import Adapter
import Message
import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val messages: MutableList<Message> = mutableListOf()
    var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = "test_id_1"

        if (adapter == null) {
            adapter = Adapter(userId!!, messages)
        }

        message_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )
        message_recycler_view.adapter = adapter

        messages.add(Message(   "test_id"
                , "receiver_id"
                , "sender_name"
                , "receiver_name"
                ,  "Automatic message"
                , "product_id"
                , "2020-03-25T22:36:34.310Z"
                , "from_device_daf87as9f4"))


        send_message_button.setOnClickListener {
            val message = text_message_input.text.toString()

            if (message.isEmpty()) {
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            messages.add(Message(   userId
                                 , "receiver_id"
                                 , "sender_name"
                                 , "receiver_name"
                                 ,  message
                                 , "product_id"
                                 , "2020-03-25T22:36:34.310Z"
                                 , "from_device_daf87as9f4"))



            text_message_input.text.clear()

            adapter!!.notifyDataSetChanged()
            message_recycler_view.scrollToPosition(messages.count() - 1)

        }
        menu_icon.setOnClickListener(){
            val popupMenu = PopupMenu(this, menu_icon)
            popupMenu.inflate(R.menu.menu_main)

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.menu_item1 -> {
                        Toast.makeText(this, "You clicked item1", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menu_item2 -> {
                        Toast.makeText(this, "You clicked item2", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

    }


}