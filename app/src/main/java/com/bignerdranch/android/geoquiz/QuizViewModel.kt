package com.bignerdranch.android.geoquiz

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel:ViewModel(){
    var Correct = 0
    var isCheater = false

    private val questionbank = listOf(
        Question(R.string.question_africa,true),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true),
        Question(R.string.question_australia,true),
        Question(R.string.question_mideast,true),
        Question(R.string.question_oceans,true)
    )

    var currentIndex = 0
    
    val currentQuestionAnswer:Boolean
    get() = questionbank[currentIndex].answer
    
    val currentQuestionText :Int
    get() = questionbank[currentIndex].textResId
    
    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionbank.size
    }
}