package com.cis.kotlinthread

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var now = System.currentTimeMillis()
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { view ->

            tv1.text = "버튼 클릭 : ${now}"
        }

        // 이렇게 하면 ANR이 된다. 오래 걸리는 작업이나 매우 많은 반복 처리는 메인 쓰레드에서 하면 안되다.
        /* while (true) {
            tv2.text = "무한 루프 : ${now}"
        }*/

        isRunning = true
        val thread1 = ThreadClass()
        thread1.start()
    }

    inner class ThreadClass : Thread() {
        override fun run() {
            while (isRunning) {
                SystemClock.sleep(100)
                var now = System.currentTimeMillis()

                Log.d("thread", "thread: ${now}")

            }
        }
    }

    // 액티비티가 종료되면 쓰레드도 멈추도록 하기 위해 지정하였음
    override fun onStop() {
        super.onStop()
        isRunning = false
    }
}
