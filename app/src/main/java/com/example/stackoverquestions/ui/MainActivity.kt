package com.example.stackoverquestions.ui

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.stackoverquestions.R
import com.example.stackoverquestions.networkutil.NetworkChecker
import com.example.stackoverquestions.networkutil.NetworkState
import com.example.stackoverquestions.ui.dialogs.NetworkErrorDialog
import com.example.stackoverquestions.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    val questionViewModel: QuestionViewModel by viewModels()
    @Inject lateinit var networkState: NetworkState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkState.networkState.observe(this, { available ->
            if(!available){
                val dialogBuilder = NetworkErrorDialog(this)
                dialogBuilder.apply {
                    setNegativeButton("Exit", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                        finish()
                    })
                    setPositiveButton("Continue Offline") { dialogInterface, i ->
                        dialogInterface.dismiss()
                        initData(false)
                    }
                    setTitle("")
                    setMessage("")
                    setCancelable(false)
                }
                val dialog = dialogBuilder.create()
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }
            else {
                initData(true)
            }
        })

    }

    fun initData(online: Boolean){
        if(online) {
            questionViewModel.getResponse().observe(this, { res ->
                if (res != null) {
                    //online -> fetch from api
                    Timber.d("initData: ${res.body?.questions}")
                    //todo update local db
                    lifecycleScope.launch(Dispatchers.IO) {
                        for (question in res.body?.questions!!)
                            questionViewModel.insertAll(question)
                    }
                } else {
                    //offline -> fetch from local db
                    questionViewModel.getAllQuestions().observe(this, { questions ->
                        Timber.d("initData: $questions")
                    })
                }
            })
        }
        else {
            //offline -> fetch from local db
            questionViewModel.getAllQuestions().observe(this, { questions ->
                Timber.d("initData: $questions")
            })
        }
    }
}