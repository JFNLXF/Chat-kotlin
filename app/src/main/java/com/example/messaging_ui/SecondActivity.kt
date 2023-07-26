package com.example.messaging_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Adapter
import Message
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    private val messages: MutableList<Message> = mutableListOf()
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = "test_id_2"

        if (adapter == null) {
            adapter = Adapter(userId, messages)
        }

        message_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        message_recycler_view.adapter = adapter

        // Add a test message for SecondActivity
        messages.add(
            Message(
                userId,
                "sender_id",
                "Receiver 2",
                "Sender 2",
                "Hello from SecondActivity!",
                "product_id",
                "2023-07-25T12:34:56.789Z",
                "from_device_1234567890"
            )
        )

        send_message_button.setOnClickListener {
            val message = text_message_input.text.toString()

            if (message.isEmpty()) {
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            messages.add(
                Message(
                    userId,
                    "receiver_id",
                    "Sender 2",
                    "Receiver 2",
                    message,
                    "product_id",
                    "2023-07-25T12:34:56.789Z",
                    "from_device_1234567890"
                )
            )

            text_message_input.text.clear()

            adapter?.notifyDataSetChanged()
            message_recycler_view.scrollToPosition(messages.size - 1)
        }
    }
}
