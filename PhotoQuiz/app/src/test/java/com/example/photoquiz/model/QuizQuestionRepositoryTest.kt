package com.example.photoquiz.model

import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test

class QuizQuestionRepositoryTest {
    private val model: QuizQuestionRepository
    private val quizData: String =
        "{\"quiz\":[{\"image\":\"l1\",\"option1\":\"PayPal\",\"option2\":\"Pandora\",\"option3\":\"Pacsafe\",\"option4\":\"Plush\",\"correct\":\"Pandora\"},{\"image\":\"l2\",\"option1\":\"Discovery\",\"option2\":\"square card\",\"option3\":\"american express\",\"option4\":\"master card\",\"correct\":\"master card\"},{\"image\":\"l3\",\"option1\":\"CNBC\",\"option2\":\"CNN\",\"option3\":\"BBC\",\"option4\":\"Fox News\",\"correct\":\"CNBC\"},{\"image\":\"l4\",\"option1\":\"GUCCI\",\"option2\":\"ARMANI\",\"option3\":\"CHANEL\",\"option4\":\"Prada\",\"correct\":\"CHANEL\"}]}"
    private lateinit var questions: QuizQuestions

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
        questions = QuizQuestions(quizQuestions)
        return questions
    }

    init {
        model = QuizQuestionRepository(loadJSONData())
    }

    @Test
    fun shouldGetNextQuestion() {
        val oldIndex = model.currentQuestionIndex
        val question = model.getNextQuestion()
        Assert.assertEquals(model.currentQuestionIndex, oldIndex + 1)
        Assert.assertEquals(
            question?.optionA,
            questions.questionList[model.currentQuestionIndex - 1].optionA
        )
        Assert.assertEquals(
            question?.optionB,
            questions.questionList[model.currentQuestionIndex - 1].optionB
        )
        Assert.assertEquals(
            question?.optionC,
            questions.questionList[model.currentQuestionIndex - 1].optionC
        )
        Assert.assertEquals(
            question?.optionD,
            questions.questionList[model.currentQuestionIndex - 1].optionD
        )
        Assert.assertEquals(
            question?.correctAnswer,
            questions.questionList[model.currentQuestionIndex - 1].correctAnswer
        )
    }

    @Test
    fun shouldCheckCorrectAnswer() {
        model.getNextQuestion()
        Assert.assertTrue(model.checkCorrectAnswer(questions.questionList[model.currentQuestionIndex - 1].correctAnswer))
    }

    @Test
    fun shouldIncrementQuestionCounter() {
        val oldValue = model.currentQuestionIndex
        model.incrementQuestionCounter()
        Assert.assertEquals(model.currentQuestionIndex, oldValue + 1)
    }

    @Test
    fun shouldReturnWhetherLastQuestionOrNot() {
        Assert.assertFalse(model.isLastQuestion())
        model.currentQuestionIndex = 36
    }

    @Test
    fun shouldReturnTrueIfLastQuestion() {
        model.currentQuestionIndex = questions.questionList.size
        Assert.assertTrue(model.isLastQuestion())
    }
}
