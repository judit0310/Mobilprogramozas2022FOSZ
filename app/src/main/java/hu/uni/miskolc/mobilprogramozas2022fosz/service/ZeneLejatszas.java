package hu.uni.miskolc.mobilprogramozas2022fosz.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Settings;

import androidx.annotation.Nullable;

import hu.uni.miskolc.mobilprogramozas2022fosz.MainActivity;

public class ZeneLejatszas  extends Service {

    private static ZeneLejatszas instance;

    public static ZeneLejatszas getInstance() {
        if(instance == null) {
            instance = new ZeneLejatszas();
        }
        return instance;
    }


    MediaPlayer player;
    Vibrator v;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.setVolume(100,100);
        player.start();
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 1000};
        v.vibrate(pattern, 0);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        v.cancel();
    }
}
