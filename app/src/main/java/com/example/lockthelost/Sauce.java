package com.example.lockthelost;

import android.os.Handler;


public class Sauce{

    private static int type;
    private double accX, accY, accZ, gyroX, gyroY, gyroZ;
    public void setAccX(double accX) {
        this.accX = accX;
    }
    public void setAccY(double accY) {
        this.accY = accY;
    }
    public void setAccZ(double accZ) {
        this.accZ = accZ;
    }
    public void setGyroX(double gyroX) {
        this.gyroX = gyroX;
    }
    public void setGyroY(double gyroY) {
        this.gyroY = gyroY;
    }
    public void setGyroZ(double gyroZ) {
        this.gyroZ = gyroZ;
    }
    public static void setType(int type) {
        Sauce.type = type;
    }
    public static int getType() {
        return type;
    }
    private double acc(double ax, double ay, double az )
    {
        return Math.sqrt((Math.pow(ax,2))+(Math.pow(ay,2))+(Math.pow(az,2)));
    }
    private double w(double wx,double wy, double wz)
    {
        return Math.sqrt((Math.pow(wx,2))+(Math.pow(wy,2))+(Math.pow(wz,2)));
    }
    public void fallDetection()
    {
        if(getType()==1)
        {
            if(acc(accX,accY,accZ)<=Constants.lftOfAcceleration){
                //calculate acceleration and angular velocity within 0.5s
                if(acc(accX,accY,accZ)>=Constants.uftOfAcceleration)
                {
                    if(w(gyroX,gyroY,gyroZ)>=Constants.uftOfAngular)
                    {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(getType()==1)
                                {
                                    //call mobile locking and fake power-off method
                                    LockTheLost lockTheLost=new LockTheLost();
                                    lockTheLost.lockTheLost();
                                }
                            }
                        }, Constants.delays);   //8 seconds from constants class
                    }
                }
            }
        }
    }



}
