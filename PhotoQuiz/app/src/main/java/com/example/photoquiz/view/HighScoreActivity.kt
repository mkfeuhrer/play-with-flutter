package com.example.photoquiz.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoquiz.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_high_score.*


class HighScoreActivity : AppCompatActivity() {

    private val objectGSON = Gson()
    private lateinit var correctAnswerScores: ArrayList<Int>
    private lateinit var wrongAnswerScores: ArrayList<Int>
    private lateinit var skippedQuestions: ArrayList<Int>
    private lateinit var totalTimeTakenList: ArrayList<Int>

    companion object {
        const val TOTAL_CORRECT = "totalCorrectResponse"
        const val TOTAL_WRONG = "totalIncorrectResponse"
        const val TOTAL_SKIPPED = "totalSkippedQuestions"
        const val TOTAL_TIME = "totalTimeTaken"
    }

    private fun getArrayList(name: String): ArrayList<Int> {
        val sharedPref: SharedPreferences =
            applicationContext.getSharedPreferences("HighScores", 0)
        val json = sharedPref.getString(name, null)
        val type = object : TypeToken<ArrayList<Int>>() {}.type
        return objectGSON.fromJson(json, type)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)
        correctAnswerScores = getArrayList(TOTAL_CORRECT)
        wrongAnswerScores = getArrayList(TOTAL_WRONG)
        skippedQuestions = getArrayList(TOTAL_SKIPPED)
        totalTimeTakenList = getArrayList(TOTAL_TIME)
        highScoreRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        highScoreRecyclerView.adapter =
            HighScoreAdapter(
                correctAnswerScores,
                wrongAnswerScores,
                skippedQuestions,
                totalTimeTakenList,
                this
            ).apply { notifyDataSetChanged() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}


