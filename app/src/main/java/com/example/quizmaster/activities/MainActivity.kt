package com.example.quizmaster.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizmaster.R
import com.example.quizmaster.adapters.quizAdapter
import com.example.quizmaster.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var fireStore: FirebaseFirestore
    lateinit var quizAdapter: quizAdapter
    private var quizList = ArrayList<Quiz>()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpViews()
        setUpFireStore()
    }


    fun setUpViews() {
        setUpDrawer()
        setUpRecyclerView()
        setUpDate()
    }

    private fun setUpDate() {
        btndatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.e("DatePicker", datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this, questionsActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.e("DatePicker", datePicker.headerText)

            }
            datePicker.addOnCancelListener {
                Log.e("DatePicker", "Date Picker Cancelled")
            }


        }
    }

    private fun setUpFireStore() {
        fireStore = FirebaseFirestore.getInstance()
        val collectionsReference = fireStore.collection("quizzes")
        collectionsReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                Log.e("ErrorFetching", "Error getting data")
                return@addSnapshotListener
            }
            Log.e("Done here", " What to do now ???")
            Log.e("Chinga", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            quizAdapter.notifyDataSetChanged()
        }
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
        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            main_drawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {//for toggling navigation drawer
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}