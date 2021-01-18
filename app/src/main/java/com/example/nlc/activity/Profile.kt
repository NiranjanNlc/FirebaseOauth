package com.example.nlc.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nlc.R
import kotlinx.android.synthetic.main.activity_profile.*


class Profile : AppCompatActivity() {

    var DISPLAY_NAME: String? = null
    var PROFILE_IMAGE_URL: String? = null
    var TAKE_IMAGE_CODE = 10001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }
}
