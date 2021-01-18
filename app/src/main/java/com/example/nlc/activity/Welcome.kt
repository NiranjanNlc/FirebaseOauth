package com.example.nlc.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.nlc.MainActivity
import com.example.nlc.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig
import com.firebase.ui.auth.AuthUI.IdpConfig.*
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class Welcome : AppCompatActivity() {

    private val TAG = "LoginRegisterActivity"
    var AUTHUI_REQUEST_CODE = 10001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun handleLoginReg(view: View)
    {
        val providers: List<IdpConfig> = Arrays.asList(
            EmailBuilder().build(),
            GoogleBuilder().build(),
            PhoneBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTosAndPrivacyPolicyUrls("https://example.com", "https://example.com")
            .setLogo(R.drawable.logo)
            .setAlwaysShowSignInMethodScreen(true)
            .setIsSmartLockEnabled(false)
            .build()

        startActivityForResult(intent, AUTHUI_REQUEST_CODE)
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int, @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTHUI_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) { // We have signed in the user or we have a new user
                val user = FirebaseAuth.getInstance().currentUser
                Log.d(TAG, "onActivityResult: " + user.toString())
                //Checking for User (New/Old)
                if (user!!.metadata!!.creationTimestamp == user.metadata!!.lastSignInTimestamp)
                {
                    //This is a New User
                } else
                { //This is a returning user

                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else { // Signing in failed
                val response = IdpResponse.fromResultIntent(data)
                if (response == null) {
                    Log.d(TAG, "onActivityResult: the user has cancelled the sign in request")
                } else {
                    Log.e(TAG, "onActivityResult: ", response.error)
                }
            }
        }
    }
}
