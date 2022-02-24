package com.example.quizmaster.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizmaster.R
import com.example.quizmaster.adapters.quizAdapter
import com.example.quizmaster.models.Quiz
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var quizAdapter: quizAdapter
    private var quizList = ArrayList<Quiz>()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        populateDummyData()
        setUpViews()
    }


    private fun populateDummyData() {
        quizList.add(Quiz("12-11-15", "Hello"))
        quizList.add(Quiz("12-11-15", "Hello"))
        quizList.add(Quiz("12-11-15", "Hello"))
        quizList.add(Quiz("12-11-15", "Hello"))
        quizList.add(Quiz("12-11-15", "Hello"))
        quizList.add(Quiz("12-11-15", "Hello"))
    }

    fun setUpViews() {
        setUpDrawer()
        setUpRecyclerView()
    }

    fun setUpRecyclerView() {
        quizAdapter = quizAdapter(this, quizList)
        quiz_recyclerview.layoutManager = GridLayoutManager(applicationContext, 2)
        quiz_recyclerview.adapter = quizAdapter
    }


    fun setUpDrawer() {
        setSupportActionBar(appBar)//custom toolbar from xml
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, main_drawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {//for toggling navigation drawer
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}