package com.example.pp03

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class Model : ViewModel() {


    private val valuesLiveData = MutableLiveData<List<Int>>()
    private val valuesLocal = mutableListOf(0, 0, 0)

    private val delay: Long=1_000
    private val repetitions = 10


    private lateinit var viewModelJob: Job

    //the scope is a must in order to ensure friendly behavior
    private lateinit var ioScope: CoroutineScope



    init {

        viewModelJob = Job()
        ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
        valuesLiveData.value =  valuesLocal
    }

    //return a random number between 0 to 10
    suspend fun randomInt(repetitions: Int, delay: Long, index: Int): Int {
        var rand = -1
        for(i: Int in 0..repetitions){
            delay(delay)
            rand = (0..10).random()

            Log.d("coroutine", "Hello from background thread"+Thread.currentThread().name)



            valuesLocal[index] = rand
            valuesLiveData.postValue(valuesLocal)



        }
        return rand
    }


    fun stop() {

        viewModelJob.cancel()
        viewModelJob = Job()
        ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    }

    fun getValues(): MutableLiveData<List<Int>>{

        return valuesLiveData
    }

    fun slotMachineDraw()  {

      //  viewModelJob?.cancel()
        //viewModelJob = Job()

       ioScope.launch {

            val leftDeffered = async {
                randomInt(repetitions,delay, 0)
            }


            val middleDeffered = async {
                randomInt(repetitions,delay, 1)
            }

            val rightDeffered = async {
                randomInt(repetitions,delay, 2)
            }

           valuesLocal[0]  = leftDeffered.await()

           valuesLocal[1]  = middleDeffered.await()

           valuesLocal[2]  = rightDeffered.await()

           valuesLiveData.postValue(valuesLocal)



        }

    }

}
