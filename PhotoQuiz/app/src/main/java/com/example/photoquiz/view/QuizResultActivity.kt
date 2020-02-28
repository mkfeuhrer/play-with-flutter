package com.example.photoquiz.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_result.*


class QuizResultActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
    }

    private val objectGSON = Gson()

    private fun saveArrayList(list: ArrayList<Int>, key: String) {
        val sharedPref: SharedPreferences =
            applicationContext.getSharedPreferences("HighScores", 0)
        val editor = sharedPref.edit()
        val json = objectGSON.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    private fun getArrayList(key: String): ArrayList<Int> {
        val sharedPref: SharedPreferences =
            applicationContext.getSharedPreferences("HighScores", 0)
        val json = sharedPref.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return objectGSON.fromJson(json, type)
    }

    companion object {
        const val TOTAL_CORRECT = "totalCorrectResponse"
        const val TOTAL_WRONG = "totalIncorrectResponse"
        const val TOTAL_SKIPPED = "totalSkippedQuestions"
        const val TOTAL_TIME = "totalTimeTaken"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.photoquiz.R.layout.activity_result)
        supportActionBar?.hide()

        val totalCorrect = intent.getIntExtra(TOTAL_CORRECT, 0)
        val totalWrong = intent.getIntExtra(TOTAL_WRONG, 0)
        val totalSkipped = intent.getIntExtra(TOTAL_SKIPPED, 0)
        val totalTimeTaken = intent.getIntExtra(TOTAL_TIME, 0)

        correctResponses.text = totalCorrect.toString()
        wrongResponses.text = totalWrong.toString()
        skippedQuestions.text = totalSkipped.toString()
        totalTimeTakenValue.text = totalTimeTaken.toString()

        val totalCorrectList = getArrayList(TOTAL_CORRECT)
        totalCorrectList.add(totalCorrect)
        val totalWrongList = getArrayList(TOTAL_WRONG)
        totalWrongList.add(totalWrong)
        val totalSkippedList = getArrayList(TOTAL_SKIPPED)
        totalSkippedList.add(totalSkipped)
        val totalTimeTakenList = getArrayList(TOTAL_TIME)
        totalTimeTakenList.add(totalTimeTaken)

        saveArrayList(totalCorrectList, TOTAL_CORRECT)
        saveArrayList(totalWrongList, TOTAL_WRONG)
        saveArrayList(totalSkippedList, TOTAL_SKIPPED)
        saveArrayList(totalTimeTakenList, TOTAL_TIME)
    }
}
