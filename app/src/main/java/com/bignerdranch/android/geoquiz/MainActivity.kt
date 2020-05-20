package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG ="MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private var Correct = 0


    private val questionbank = listOf(
        Question(R.string.question_africa,true),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true),
        Question(R.string.question_australia,true),
        Question(R.string.question_mideast,true),
        Question(R.string.question_oceans,true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById((R.id.question_text_view))

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            setQuestionForward()
        }

        questionTextView.setOnClickListener {
            setQuestionForward()
        }

        updateQuestion()
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun setQuestionForward(){
        if (currentIndex == questionbank.size-1) {
            var temp = questionbank.size
            Toast.makeText(
                this,
                "$Correct of $temp correct",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            currentIndex = (currentIndex + 1) % questionbank.size
            updateQuestion()
        }
    }

    private fun updateQuestion(){
        trueButton.isClickable = true
        falseButton.isClickable = true
        questionTextView.setText((questionbank[currentIndex].textResId))
    }

    private fun checkAnswer(userAnswer : Boolean){
        trueButton.isClickable = false
        falseButton.isClickable = false

        if (userAnswer == questionbank[currentIndex].answer)
            Correct += 1

        Toast.makeText(this,
           if (userAnswer == questionbank[currentIndex].answer) R.string.correct_toast else R.string.incorrect_toast,
        Toast.LENGTH_SHORT).show()
    }
}
