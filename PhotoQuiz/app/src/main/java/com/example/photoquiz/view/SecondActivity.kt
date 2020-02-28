package com.example.photoquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.photoquiz.contract.ContractInterface
import com.example.photoquiz.controller.QuizController
import kotlinx.android.synthetic.main.activity_second.*
import com.example.photoquiz.R
import com.example.photoquiz.model.QuizQuestion
import android.content.Intent
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity(), ContractInterface.View {

    private lateinit var parentLayout: View

    override fun initializeView(function: (String) -> Unit) {
        arrayOf(choice1, choice2, choice3, choice4).forEach { button ->
            button.setOnClickListener {
                function(button.text.toString())
            }
        }
    }

    private lateinit var quizController: QuizController

    override fun setTimer(time: String) {
        timer.text = time
    }

    override fun disableButtons() {
        choice1.isEnabled = false
        choice2.isEnabled = false
        choice3.isEnabled = false
        choice4.isEnabled = false
    }

    override fun showResultPage(
        correctChoice: Int,
        incorrectChoice: Int,
        questionsSkipped: Int,
        totalTimeTaken: Int
    ) {
        val intent = Intent(applicationContext, QuizResultActivity::class.java)
        intent.putExtra("totalCorrectResponse", correctChoice)
        intent.putExtra("totalIncorrectResponse", incorrectChoice)
        intent.putExtra("totalSkippedQuestions", questionsSkipped)
        intent.putExtra("totalTimeTaken", totalTimeTaken)
        finish()
        startActivity(intent)
    }

    override fun showResult(name: String) {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        popupImage.startAnimation(fadeIn)
        val res = resources.getIdentifier(name, "drawable", packageName)
        popupImage.setImageDrawable(ContextCompat.getDrawable(this, res))
        popupImage.translationZ = 20f
        popupImage.visibility = View.VISIBLE
    }

    override fun animateGameScreen() {
        val slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        val slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out)
        game_screen.startAnimation(slideIn)
        game_screen.startAnimation(slideOut)
    }

    override fun updateViewWithNextQuestion(question: QuizQuestion?) {
        popupImage.visibility = View.GONE
        choice1.isEnabled = true
        choice2.isEnabled = true
        choice3.isEnabled = true
        choice4.isEnabled = true
        if (question != null) {
            choice1.text = question.optionA
            choice2.text = question.optionB
            choice3.text = question.optionC
            choice4.text = question.optionD
            image_question_1.setImageResource(
                resources.getIdentifier(
                    question.imageId,
                    "drawable",
                    packageName
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()
        parentLayout = findViewById<View>(android.R.id.content)
        val quizData: String = this.assets.open("quiz_data.json").bufferedReader().readText()
        quizController = QuizController(quizData, this)
        updateViewWithNextQuestion(quizController.getQuestion())
    }
}
