package com.example.asyncandlivedatademo

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.*



//This class shows off three different ways of performing short lived background tasks.

class MyViewModel: ViewModel(){


    //Java 'Timer'
    private var timer = Timer()

    //Live data for passing seconds to DisplayFragment
    private val mElapsedTime = MutableLiveData<Long>()

    //Live data for saving laps
    private val mLap = MutableLiveData<String>()



    //1000ms
    private val ONE_SECOND = 1000

    private var running = false

    //constructor call
    init {
        mLap.value = "Laps:"
        mElapsedTime.value= 0
    }

    //ControlFragment will invoke this
    fun startstop(){
        if(running){
            timer.cancel()
            running=!running
        }
        else{
            mElapsedTime.value= 0
            //after calling 'cancel' on timer we need to create a new instance
            timer = Timer()
            start()
            running=!running
        }
    }

    private fun start(){
        // Option 1 -- Timer : Update the elapsed time every second.
        timer.scheduleAtFixedRate(object : TimerTask() {

            var counter: Long = 0
            // runs on a non-UI thread.
            override fun run() {

                counter++
                //'posting' new values every second to the MutableLiveData object
                // notice that this is happening on a non-UI thread.
                mElapsedTime.postValue(counter)
            }
            //delay              //period
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }



    //DisplayFragment will use this method to retrieve the live data object.
    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }


// Option 2 -- Kotlin coroutine to capture laps-----------------------

    //https://proandroiddev.com/android-coroutine-recipes-33467a4302e9
    //https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#4

    private val viewModelJob = Job()

    //the scope is a must in order to ensure friendly behavior
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun slowLapCapture() {


        ioScope.launch { // launch new coroutine in background and continue
            // it will be automatically killed if viewmodel is destroyed.
            delay(1000L)
            updateLiveData(mElapsedTime.value.toString())

            }
    }

    private fun updateLiveData(lap:String?){
        lap?.let { mLap.postValue( mLap.value+" "+it) }
    }

    fun getLapTime():MutableLiveData<String>{
        return mLap
    }

    //cancelling the coroutine
    fun fastCancel(){
        ioScope.coroutineContext.cancel()
            }

    // -------------------------------


    // Option 3(outdated): AsyncTask -------------------------------------

    //an outdated way of performing short-lived async tasks
    class slowClearTask : AsyncTask<MutableLiveData<String>, Void, Void>() {


        override fun doInBackground(vararg params: MutableLiveData<String>?): Void? {
            //Delaying should happen in a java way
            Thread.sleep(1000)
            params[0]?.postValue("Laps:")
            return null
        }
        override fun onPreExecute() {
            super.onPreExecute()
                    }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
              }
    }


    // ConrolFragment calls this to reset the laps.
    fun slowClear(){
        slowClearTask().execute(mLap)
    }

    // -----------------------------------------------






}