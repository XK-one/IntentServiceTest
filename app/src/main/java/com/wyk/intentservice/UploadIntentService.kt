package com.wyk.intentservice

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log

class UploadIntentService(): IntentService("UploadIntentService"){

    companion object {
        val TAG = "wyk"
        val ACTION_UPLOAD_IMG: String = "com.wyk.intentservice.action.UPLOAD_IMAGE"
        val EXTRA_IMG_PATH: String  = "com.wyk.intentservice.extra.IMG_PATH"
        fun startUploadService(context: Context, path: String){
            val intent = Intent(context, UploadIntentService::class.java)
            intent.setAction(ACTION_UPLOAD_IMG)
            intent.putExtra(EXTRA_IMG_PATH, path)
            context.startService(intent)
        }
    }

    /**
     * 源码注释:
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     * 也就是说存在队列里的请求都处理完了，IntentService会关闭，无须调用stopSelf
     */
    override fun onHandleIntent(intent: Intent?) {
        if(intent != null){
            val action = intent.action
            if(!TextUtils.equals(action, ACTION_UPLOAD_IMG))return
            val path = intent.getStringExtra(EXTRA_IMG_PATH)
            handleUploadImg(path)
        }
    }
    /**上传图片*/
    fun handleUploadImg(path: String){
        Thread.sleep(3000)
        val intent = Intent(MainActivity.UPLOAD_RESULT)
        intent.putExtra(EXTRA_IMG_PATH, path)
        sendBroadcast(intent)
    }

    override fun onCreate() {
        Log.i(TAG, "UploadIntentService-->onCreate")
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.i(TAG, "UploadIntentService-->onStart,startId ==${startId}")
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "UploadIntentService-->onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i(TAG, "UploadIntentService-->onDestroy")
        super.onDestroy()
    }
}