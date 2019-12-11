package com.benpike.ben_pike_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
//import javax.swing.UIManager.put
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    // connect to firestore
    var db = FirebaseFirestore.getInstance()

    override fun onStart() {
        super.onStart()

        // check for authenticated user
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            val intent = Intent(applicationContext, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}
