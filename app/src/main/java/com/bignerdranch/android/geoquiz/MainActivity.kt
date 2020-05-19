package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var questionTextView: TextView

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
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
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

        previousButton.setOnClickListener {
            setQuestionBackward()
        }

        questionTextView.setOnClickListener {
            setQuestionForward()
        }

        updateQuestion()
    }

    private fun setQuestionForward(){
        currentIndex = currentIndex++ % questionbank.size
        updateQuestion()
    }

    private fun setQuestionBackward(){
        currentIndex = currentIndex-- % questionbank.size
        updateQuestion()
    }

    private fun updateQuestion(){
        questionTextView.setText((questionbank[currentIndex].textResId))
    }

    private fun checkAnswer(userAnswer : Boolean){
        Toast.makeText(this,
           if (userAnswer == questionbank[currentIndex].answer) R.string.correct_toast else R.string.incorrect_toast,
        Toast.LENGTH_SHORT).show()
    }
}
