package com.digitalsamurai.reklaintest.activityLoading

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.digitalsamurai.reklaintest.R
import com.digitalsamurai.reklaintest.activityBrowser.WebActivity
import com.digitalsamurai.reklaintest.activityGame.GameActivity

class LoadingActivity : AppCompatActivity() {
    lateinit var channel : NotificationChannel
    val channelId = "ReklainTest"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("Loading...")

        //Intent для пуш уведомления
        val intent = Intent(this,WebActivity::class.java)
        intent.putExtra("FromPush",true)

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


        //уважаемое пуш уведомление
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_web_black)
            .setContentTitle("Web")
            .setContentText("Click to open web")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        //создаем канал
        with(getSystemService(NOTIFICATION_SERVICE) as NotificationManager){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            channel = NotificationChannel(channelId,"reklainTest", NotificationManager.IMPORTANCE_DEFAULT)
            createNotificationChannel(channel)
        }
            notify(1, builder.build())
        }


        //если когда-то был запущен веб через пуш, то запускаем его, иначе запускаем игру
        with(this.getSharedPreferences("ReklainTest", MODE_PRIVATE)){
            if (getBoolean("FromPush", false)){
                startActivity(Intent(this@LoadingActivity,WebActivity::class.java))
            } else{
                startActivity(Intent(this@LoadingActivity,GameActivity::class.java))

            }
        }
    }
}