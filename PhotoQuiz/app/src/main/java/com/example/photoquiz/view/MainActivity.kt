package com.example.photoquiz.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.photoquiz.R
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private val objectGSON = Gson()
    private val emptyArrayList = ArrayList<Int>()

    companion object {
        const val TOTAL_CORRECT = "totalCorrectResponse"
        const val TOTAL_WRONG = "totalIncorrectResponse"
        const val TOTAL_SKIPPED = "totalSkippedQuestions"
        const val TOTAL_TIME = "totalTimeTaken"
    }

    private fun saveArrayList(list: ArrayList<Int>, key: String) {
        val sharedPref: SharedPreferences =
            applicationContext.getSharedPreferences("HighScores", 0)
        val editor = sharedPref.edit()
        val json = objectGSON.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveArrayList(emptyArrayList, TOTAL_CORRECT)
        saveArrayList(emptyArrayList, TOTAL_WRONG)
        saveArrayList(emptyArrayList, TOTAL_SKIPPED)
        saveArrayList(emptyArrayList, TOTAL_TIME)

        val imageView = findViewById<ImageView>(R.id.gojekImage)
        val imgResId = R.drawable.gojek
        imageView.setImageResource(imgResId)

        button.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            startActivity(intent)
        }

        highScores.setOnClickListener {
            val intent = Intent(applicationContext, HighScoreActivity::class.java)
            startActivity(intent)
        }
    }
}
