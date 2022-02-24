package com.example.quizmaster.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.models.Quiz
import com.example.quizmaster.utils.colorPicker
import com.example.quizmaster.utils.iconPicker

class quizAdapter(val context: Context, val quizzes: ArrayList<Quiz>) :
    RecyclerView.Adapter<quizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textTitle.text = quizzes[position].title
        holder.iconview.setImageResource(iconPicker.getIcon())
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorPicker.getColor().toString()))
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView = itemView.findViewById(R.id.quiz_title)
        var iconview: ImageView = itemView.findViewById(R.id.quiz_icon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }


}