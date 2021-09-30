package com.example.lockthelost;

import android.app.admin.DevicePolicyManager;
import android.content.*;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;

import org.jetbrains.annotations.NotNull;

import java.io.DataOutputStream;
import java.lang.reflect.InvocationTargetException;

public class LockTheLost  {


    private boolean lost;
    public void setLost(boolean lost) {
        this.lost = lost;
    }
    public boolean getLost() {
        return lost;
    }

    public void lockTheLost() {
        setLost(true);
        turnGPSOn();
        enableMobileData();

        ContextWrapper contextWrapper = new ContextWrapper(null);
        AudioManager audio_mngr = (AudioManager) contextWrapper.getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audio_mngr .setRingerMode(AudioManager.RINGER_MODE_SILENT);

        if(false){
            turnGPSOff();
            lock();




        }
    }


    public void lock()
    {
        DevicePolicyManager abc = null;
        assert abc != null;
        abc.lockNow();

    }

    public ContentResolver getContentResolver()
    {

        return null;
    };

    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            Context context=null;
            assert context != null;
            context.sendBroadcast(poke);

        }
    }

    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            Context context=null;
            assert context != null;
            context.sendBroadcast(poke);
        }
    }



    private void enableMobileData(){

        try {
            String[] cmds = {"svc data enable"};
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            for (String tmpCmd : cmds) {
                os.writeBytes(tmpCmd + "\n");
            }
            os.writeBytes("exit\n");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean dispatchKeyEvent(@NotNull KeyEvent event) {
        int keyPressed = event.getKeyCode();
        if(keyPressed==KeyEvent.KEYCODE_POWER) {
            Log.d("###", "Power button long click");
            if (getLost()) {
                //send broadcast to close all dialogs
                Context context =null;
                assert context != null;
                context.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //
                    }
                }, Constants.delays);

                //call fake powerOff menu dialog box
               goToSleep(context);
                return true;

                //
            }
        }
       return false;
    }

    public static void goToSleep(Context context) {
        PowerManager powerManager= (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        try {
            powerManager.getClass().getMethod("goToSleep", new Class[]{long.class}).invoke(powerManager, SystemClock.uptimeMillis());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }




    
    public void getDatabasePassword()
    {
        //StudentDatabase.getDbInstance(getApplicationContext()).studentsDao().getAllStudents();
    }
    public void setDatabasePassword(String databasePassword)
    {
        //PasswordDb.getDatabaseInstance(getApplicationContext()).passwordDao().getPassword();
    }
    
    
    

    

    





}
