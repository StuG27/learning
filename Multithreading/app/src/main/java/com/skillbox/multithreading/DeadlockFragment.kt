package com.skillbox.multithreading

import android.util.Log
import androidx.fragment.app.Fragment

class DeadlockFragment : Fragment() {

    private var i = 0
    private val lock1 = Any()
    private val lock2 = Any()

    override fun onResume() {
        super.onResume()

        val thread1 = Thread {
            Log.d("Deadlock", "Start1")
            (0..1000000).forEach {
                synchronized(lock1) {
                    synchronized(lock2) {
                        i++
                    }
                }
            }
            Log.d("Deadlock", "End1")
        }
        val thread2 = Thread {
            Log.d("Deadlock", "Start2")
            (0..1000000).forEach {
                synchronized(lock2) {
                    synchronized(lock1) {
                        i++
                    }
                }
            }

            Log.d("Deadlock", "End2")
        }
        thread1.start()
        thread2.start()

//      Fixed Deadlock

//        val thread1 = Thread {
//            Log.d("Deadlock", "Start1")
//            (0..1000000).forEach {
//                synchronized(this) {
//                    i++
//                }
//            }
//            Log.d("Deadlock", "End1")
//        }
//        val thread2 = Thread {
//            Log.d("Deadlock", "Start2")
//            (0..1000000).forEach {
//                synchronized(this) {
//                    i++
//                }
//            }
//
//            Log.d("Deadlock", "End2")
//        }
//        thread1.start()
//        thread2.start()
    }
}