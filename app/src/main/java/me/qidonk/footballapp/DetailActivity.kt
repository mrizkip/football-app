package me.qidonk.footballapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    lateinit var mImage: ImageView
    lateinit var mNameText: TextView
    lateinit var mDescText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            lparams(matchParent, matchParent)
            padding = dip(16)

            mImage = imageView {
                padding = dip(16)
            }.lparams(matchParent, 150)

            mNameText = textView {
                textColor = Color.BLACK
                textSize = 18f
            }.lparams(width = wrapContent) {
                gravity = Gravity.CENTER
                topMargin = dip(16)
            }

            mDescText = textView {
                textColor = Color.BLACK
            }.lparams(width = wrapContent) {
                topMargin = dip(16)
            }
        }

        val intent = intent
        val name = intent.getStringExtra("name")
        val image = intent.getIntExtra("image", 0)
        val desc = intent.getStringExtra("desc")

        Log.d("DetailActivity", "image: $image")

        mNameText.text = name
        Picasso.get().load(image) to mImage
        mDescText.text = desc
    }


}
