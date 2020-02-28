package com.example.photoquiz.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.photoquiz.R
import kotlinx.android.synthetic.main.score_list_item.view.*

class HighScoreAdapter(
    private val correctAnswers: ArrayList<Int>,
    private val wrongAnswers: ArrayList<Int>,
    private val skippedQuestions: ArrayList<Int>,
    private val totalTimeTaken: ArrayList<Int>,
    private val context: HighScoreActivity
) :
    RecyclerView.Adapter<ScoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.score_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return correctAnswers.size
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val result: String =
            correctAnswers[position].toString() + " - " + wrongAnswers[position].toString() + " - " + skippedQuestions[position].toString() + " - " + totalTimeTaken[position].toString()
        holder.correctScoreValue.text = result
    }
}

class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val correctScoreValue: TextView = view.correct_score_value
}
