package com.example.quizmaster.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizmaster.R
import com.example.quizmaster.adapters.OptionsAdapter
import com.example.quizmaster.models.Question
import com.example.quizmaster.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_questions.*

class questionsActivity : AppCompatActivity() {
    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var current_question = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        setUpFireStore()
        setUpEventListeners()
    }

    private fun setUpEventListeners() {
        btnPrevious.setOnClickListener {
            current_question--
            bindViews()
        }
        btnNext.setOnClickListener {
            current_question++
            bindViews()
        }
        btnSubmit.setOnClickListener {
            Log.e("FINALQUiz", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0]) // serialize to string
            intent.putExtra("Quiz", json)
            startActivity(intent)
        }
    }

    private fun setUpFireStore() {
        val fireStore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            fireStore.collection("quizzes")
                .whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }


    private fun bindViews() {
        btnNext.visibility = View.GONE
        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE

        if (current_question == 1) {
            btnNext.visibility = View.VISIBLE
        } else if (current_question == questions!!.size) {
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        } else {
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$current_question"]
        question?.let {
            description.text = question.description
            val optionsAdapter = OptionsAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionsAdapter
            optionList.setHasFixedSize(true)
        }
    }
}