package ds.urltobitmap

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class FirstActivity : AppCompatActivity() {

    val url =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Steinadler_Aquila_chrysaetos_closeup1_Richard_Bartz.jpg/260px-Steinadler_Aquila_chrysaetos_closeup1_Richard_Bartz.jpg"

    val dialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(this).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        button.setOnClickListener {
            dialog.show()
            GlobalScope.async {
                MainActivity.thumburl = getBitmapFromURL(url)
                runOnUiThread {
                    startActivity(Intent(this@FirstActivity, MainActivity::class.java))
                    dialog.dismiss()
                }
            }
        }
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            null
        }

    }
}
