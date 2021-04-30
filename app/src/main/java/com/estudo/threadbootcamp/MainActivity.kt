package com.estudo.threadbootcamp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.estudo.model.AstroPeople

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadButton = findViewById<Button>(R.id.button_load_data)
        loadButton.setOnClickListener {
            launchAstroTask()
        }

    }

    fun showData(list: List<AstroPeople>?) {
        val textData = findViewById<TextView>(R.id.text_data)
        textData.text = ""
        list?.forEach { people ->
            textData.append("${people.name} - ${people.craft} \n\n")
        }
    }

    fun showLoadingIndicator() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_main)
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoadingIndicator() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_main)
        progressBar.visibility = View.GONE
    }

    fun launchAstroTask() {
        val task = TaskAstro()
        task.execute()
    }

    inner class TaskAstro() : AsyncTask<Void, Int, List<AstroPeople>>() {

        private val repository = AstroRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        override fun doInBackground(vararg params: Void?): List<AstroPeople> {
            //onProgressUpdate(0)
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstroPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }
    }
}