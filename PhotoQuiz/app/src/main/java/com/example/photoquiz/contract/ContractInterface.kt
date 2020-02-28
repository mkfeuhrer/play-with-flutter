package com.example.photoquiz.contract

import com.example.photoquiz.model.QuizQuestion

interface ContractInterface {

    interface View {
        fun initializeView(function: (String) -> Unit)
        fun updateViewWithNextQuestion(question: QuizQuestion?)
        fun setTimer(time: String)
        fun showResult(name: String)
        fun animateGameScreen()
        fun disableButtons()
        fun showResultPage(
            correctChoice: Int,
            incorrectChoice: Int,
            questionsSkipped: Int,
            totalTimeTaken: Int
        )
    }

    interface Controller {
        fun getQuestion(): QuizQuestion?
        fun optionButtonClick(selectedOption: String)
    }

    interface Model {
        fun getNextQuestion(): QuizQuestion?
        fun checkCorrectAnswer(selectedOption: String): Boolean
        fun incrementQuestionCounter()
        fun isLastQuestion(): Boolean
    }
}
