package com.caleidos.firebaseapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = intent.extras
        if (bundle != null) {
            configData(intent)
        }
        FirebaseMessaging.getInstance().subscribeToTopic("cleidos")

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            findViewById<AppCompatTextView>(R.id.textToken).text = token
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        configData(intent = intent)
    }

    private fun configData(intent: Intent?) {
        val title = intent?.getStringExtra("title")
        val body = intent?.getStringExtra("body")
        val imageUrl = intent?.getStringExtra("image")
        findViewById<AppCompatTextView>(R.id.pushText).text =
            "Title: $title\nBody: $body\nLink: $imageUrl"
    }
}