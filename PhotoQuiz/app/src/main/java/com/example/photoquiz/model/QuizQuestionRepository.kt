package com.example.photoquiz.model

import com.example.photoquiz.contract.ContractInterface

class QuizQuestionRepository(private val quizQuestions: QuizQuestions) : ContractInterface.Model {

    var currentQuestionIndex = 0

    override fun getNextQuestion(): QuizQuestion? {
        return if (currentQuestionIndex < quizQuestions.questionList.size) {
            val question: QuizQuestion = quizQuestions.questionList[currentQuestionIndex]
            incrementQuestionCounter()
            question
        } else null
    }

    override fun checkCorrectAnswer(selectedOption: String): Boolean {
        return quizQuestions.questionList[currentQuestionIndex - 1].correctAnswer == selectedOption
    }

    override fun incrementQuestionCounter() {
        currentQuestionIndex++
    }

    override fun isLastQuestion(): Boolean {
        return currentQuestionIndex == quizQuestions.questionList.size
    }
}
