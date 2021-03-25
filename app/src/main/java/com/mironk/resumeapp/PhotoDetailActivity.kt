package com.mironk.resumeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PhotoDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        supportActionBar?.hide()

        val image = intent.getParcelableExtra<PortfolioList>(PortfolioFragment.INTENT_PARCELABLE)

        val imgSrc = findViewById<ImageView>(R.id.image_app)
        val imgTitle = findViewById<TextView>(R.id.title_app)

        imgSrc.setImageResource(image!!.imageSrc)
        imgTitle.text = image.title
    }
}