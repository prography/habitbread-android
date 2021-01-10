package com.example.newhabitbread.api

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.newhabitbread.R
import com.example.newhabitbread.base.BaseApplication
import com.example.newhabitbread.ui.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.habitbread.main.data.UserInfoRequest
import com.habitbread.main.data.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirebaseAPI: FirebaseMessagingService() {
    private val TAG="HabitBread"

    override fun onNewToken(token:String){
        super.onNewToken(token)
        Log.d("$TAG:newFCMToken",token)

        BaseApplication.preferences.FCMToken=token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Send android notification when received FCM during foreground
        var notificationInfo: Map<String, String> = mapOf()
        remoteMessage.notification?.let {
            // Set android notification title, body using FCM title, body
            notificationInfo = mapOf(
                "title" to it.title.toString(),
                "body" to it.body.toString()
            )
            sendNotification(notificationInfo)
        }
    }

    fun sendRegistrationToServer(token: String) {
        val HabitBreadAPI = ServerImpl.APIService
        val call: Call<UserInfoResponse> = HabitBreadAPI.patchFcmToken(UserInfoRequest.FcmTokenRequest(token))

        call.enqueue(
            object : Callback<UserInfoResponse> {
                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    Log.d(TAG, "Fail patch new token")

                }

                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    Log.d(TAG, "Success patch new token")
                }
            }
        )

    }

    private fun sendNotification(messageBody: Map<String, String>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
        PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    // Set FCM title, body data
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.icon_fcm_push)
        .setContentTitle(messageBody["title"])
        .setContentText(messageBody["body"])
        .setAutoCancel(true)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Since android Oreo notification channel is needed.
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}