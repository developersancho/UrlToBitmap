package ds.urltobitmap

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Callback
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    val imageUrl =
        "https://m.media-amazon.com/images/S/aplus-media/vc/183da200-f9d3-4b68-a313-35f685d446d3._SL220__.jpg"

    companion object {
        var thumburl: Bitmap? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val placeImage: Drawable = BitmapDrawable(resources, thumburl)
        Picasso.get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .placeholder(placeImage)
            .into(iv, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE
                }

            })

    }
}
