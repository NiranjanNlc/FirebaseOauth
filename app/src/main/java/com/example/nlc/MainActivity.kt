package com.example.nlc

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nlc.activity.Profile
import com.example.nlc.activity.Welcome
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),FirebaseAuth.AuthStateListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
         var firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.getCurrentUser() == null) {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
            return;
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {
            R.id.action_logout -> {
                Toast.makeText(applicationContext, "Hello before logout", Toast.LENGTH_LONG).show()
             //   FirebaseAuth.getInstance().signOut()
                AuthUI.getInstance().signOut(this)
                return true
            }
            R.id.action_profile -> {
                startActivity(Intent(this@MainActivity, Profile::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(this)
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener(this)

    }

    private fun startLoginActivity() {
        val intent = Intent(this, Welcome::class.java)
        startActivity(intent)
        finish()
    }

    override fun onAuthStateChanged(p0: FirebaseAuth)
    {
        Toast.makeText(applicationContext, "Hello ${p0.currentUser}", Toast.LENGTH_LONG).show()
        if (p0.getCurrentUser() == null) {
            Toast.makeText(applicationContext, "Hello user it null", Toast.LENGTH_LONG).show()
            startLoginActivity();
            return;
        }

    }
}
