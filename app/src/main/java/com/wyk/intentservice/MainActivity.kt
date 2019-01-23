package com.wyk.intentservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://blog.csdn.net/lmj623565791/article/details/47143563
 */
class MainActivity : AppCompatActivity() {

    companion object {
        val UPLOAD_RESULT: String = "com.wyk.intentservice.UPLOAD_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver()
    }

    private fun registerReceiver() {
        val filter = IntentFilter(UPLOAD_RESULT)
        registerReceiver(mReceiver, filter)
    }


    val mReceiver: BroadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val path = intent?.getStringExtra(UploadIntentService.EXTRA_IMG_PATH)
            val textView = id_ll_taskcontainer.findViewWithTag<TextView>(path)
            textView.text = "${path} upload success"
        }
    }

    var i: Int = 0
    fun addTask(view: View){
        val path: String = "/sdcard/imgs/${++i}.png"
        UploadIntentService.startUploadService(this, path)

        val textView = TextView(this)
        id_ll_taskcontainer.addView(textView)
        textView.setText("${path} is uploading ...")
        textView.setTag(path)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }
}
