package com.example.quizmaster.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.models.Question

class OptionsAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {

    private var options: List<String> =
        listOf(question.option1, question.option2, question.option3, question.option4)


    inner class OptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var optionsView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.options_item, parent, false)
        return OptionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.optionsView.text = options[position]
        holder.itemView.setOnClickListener {
            Log.e("holder", holder.optionsView.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }


}