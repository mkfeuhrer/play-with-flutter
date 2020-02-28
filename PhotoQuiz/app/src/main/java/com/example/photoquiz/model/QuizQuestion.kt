package com.example.photoquiz.model

data class QuizQuestion(
    val imageId: String,
    val correctAnswer: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String
)

data class QuizQuestions(
    val questionList: List<QuizQuestion>
)
