package com.example.photoquiz.controller

import android.os.CountDownTimer
import com.example.photoquiz.contract.ContractInterface
import com.example.photoquiz.model.QuizQuestion
import com.example.photoquiz.model.QuizQuestionRepository
import com.example.photoquiz.model.QuizQuestions
import org.json.JSONArray
import org.json.JSONObject

class QuizController(private val quizData: String, _view: ContractInterface.View) :
    ContractInterface.Controller {

    private var view: ContractInterface.View = _view
    private lateinit var questions: QuizQuestions
    private var model: QuizQuestionRepository
    private var timer: CountDownTimer
    private var correctChoice = 0
    private var incorrectChoice = 0
    private var questionsSkipped = 0
    private var totalTimeTaken = 0
    private var timeLeft = 0
    private lateinit var timer2: CountDownTimer

    private fun loadJSONData(): QuizQuestions {
        val quizQuestions: MutableList<QuizQuestion> = ArrayList()
        val quizQuestionsJSONArray: JSONArray = JSONObject(quizData).getJSONArray("quiz")
        for (item in 0 until quizQuestionsJSONArray.length()) {
            val data = quizQuestionsJSONArray.getJSONObject(item)
            val question = QuizQuestion(
                data.get("image").toString(),
                data.get("correct").toString(),
                data.get("option1").toString(),
                data.get("option2").toString(),
                data.get("option3").toString(),
                data.get("option4").toString()
            )
            quizQuestions.add(question)
        }
        questions = QuizQuestions(quizQuestions.subList(0, 5))
        return questions
    }

    init {
        model = QuizQuestionRepository(loadJSONData())
        view.initializeView { selectedOption -> optionButtonClick(selectedOption) }
        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                view.setTimer("Time Left: " + millisUntilFinished / 1000)
                timeLeft = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                view.setTimer("Time's Up")
                questionsSkipped++
                totalTimeTaken += 10
                checkLastQuestion()
            }
        }
    }

    override fun getQuestion(): QuizQuestion? {
        return if (model.isLastQuestion()) {
            null
        } else {
            timer.start()
            model.getNextQuestion()
        }
    }

    override fun optionButtonClick(selectedOption: String) {
        view.disableButtons()
        timer.cancel()
        totalTimeTaken += 10 - timeLeft
        if (model.checkCorrectAnswer(selectedOption)) {
            correctChoice++
            view.showResult("correct")
        } else {
            incorrectChoice++
            view.showResult("wrong")
        }
        checkLastQuestion()
    }

    private fun checkLastQuestion() {
        if (model.isLastQuestion()) {
            timer2.cancel()
            view.showResultPage(correctChoice, incorrectChoice, questionsSkipped, totalTimeTaken)
        } else {
            timer2 = object : CountDownTimer(1100, 1000) {
                override fun onTick(p0: Long) {
                }

                override fun onFinish() {
                    view.updateViewWithNextQuestion(getQuestion())
                    view.animateGameScreen()
                }
            }
            timer2.start()
        }
    }
}
