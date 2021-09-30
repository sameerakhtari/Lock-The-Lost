package com.example.lockthelost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.location.DetectedActivity;



public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private String TAG = MainActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;

    private TextView txtActivity, txtConfidence;
    private ImageView imgActivity;
    private Button btnStartTrcking, btnStopTracking;

    //---------------------------------------------------

    // System sensor manager instance.
    private SensorManager mSensorManager;

    // Proximity and light sensors, as retrieved from the sensor manager.
    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorAmbientTemperature;
    private Sensor mSensorGravity;
    private Sensor mSensorGyroscope;
    private Sensor mSensorLinearAcceleration;
    private Sensor mSensorMagneticField;
    private Sensor mSensorOrientation;
    private Sensor mSensorPressure;
    private Sensor mSensorRelativeHumidity;
    private Sensor mSensorRotationVector;
    private Sensor mSensorTemperature;
    private Sensor mSensorGameRotationVector;
    private Sensor mSensorGeomagneticRotationVector;

    //add here more sensor variables

    // TextViews to display current sensor values.
    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorAccelerometer;
    private TextView mTextSensorAmbientTemperature;
    private TextView mTextSensorGravity;
    private TextView mTextSensorGyroscope;
    private TextView mTextSensorLinearAcceleration;
    private TextView mTextSensorMagneticField;
    private TextView mTextSensorOrientation;
    private TextView mTextSensorPressure;
    private TextView mTextSensorRelativeHumidity;
    private TextView mTextSensorRotationVector;
    private TextView mTextSensorTemperature;
    private TextView mTextSensorGameRotationVector;
    private TextView mTextSensorGeomagneticRotationVector;

    int activityType;

    @RequiresApi(api = Build.VERSION_CODES.Q)


    //-----------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      ///////////////////////here///////////////here///////here
        setContentView(R.layout.start);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                setContentView(R.layout.activity_main);



        btnStartTrcking = findViewById(R.id.btn_start_tracking);
                btnStopTracking = findViewById(R.id.btn_stop_tracking);

        //--START-------------------------------------------------------


        // Initialize all view variables.
        /*
        mTextSensorLight = (TextView) findViewById(R.id.label_light);
        mTextSensorProximity = (TextView) findViewById(R.id.label_proximity);
        mTextSensorAccelerometer = (TextView) findViewById(R.id.label_accelerometer);
        mTextSensorAmbientTemperature = (TextView) findViewById(R.id.label_ambientTemperature);
        mTextSensorGravity = (TextView) findViewById(R.id.label_gravity);
        mTextSensorGyroscope = (TextView) findViewById(R.id.label_gyroscope);
        mTextSensorLinearAcceleration = (TextView) findViewById(R.id.label_linearAcceleration);
        mTextSensorMagneticField = (TextView) findViewById(R.id.label_magneticField);
        mTextSensorOrientation = (TextView) findViewById(R.id.label_orientation);
        mTextSensorPressure = (TextView) findViewById(R.id.label_pressure);
        mTextSensorRelativeHumidity = (TextView) findViewById(R.id.label_relativeHumidity);
        mTextSensorRotationVector = (TextView) findViewById(R.id.label_rotationVector);
        mTextSensorTemperature = (TextView) findViewById(R.id.label_temperature);
        mTextSensorGameRotationVector = (TextView) findViewById(R.id.label_gameRotationVector);
        mTextSensorGeomagneticRotationVector = (TextView) findViewById(R.id.label_geomagneticRotationVector);

        */

        // Get an instance of the sensor manager.
        mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);

        // Get light and proximity sensors from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor
        // is not available on the device.
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorAmbientTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorLinearAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorRelativeHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mSensorRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mSensorTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        mSensorGameRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        mSensorGeomagneticRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);


        // Get the error message from string resources.
        String sensor_error = getResources().getString(R.string.error_no_sensor);

        // If either mSensorLight or mSensorProximity are null, those sensors
        // are not available in the device.  Set the text to the error message
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorAccelerometer == null) {
            mTextSensorAccelerometer.setText(sensor_error);
        }
        if (mSensorAmbientTemperature == null) {
            mTextSensorAmbientTemperature.setText(sensor_error);
        }
        if (mSensorGravity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorGyroscope == null) {
            mTextSensorGyroscope.setText(sensor_error);
        }
        if (mSensorLinearAcceleration == null) {
            mTextSensorLinearAcceleration.setText(sensor_error);
        }
        if (mSensorMagneticField == null) {
            mTextSensorMagneticField.setText(sensor_error);
        }
        if (mSensorOrientation == null) {
            mTextSensorOrientation.setText(sensor_error);
        }
        if (mSensorPressure == null) {
            mTextSensorPressure.setText(sensor_error);
        }
        if (mSensorRelativeHumidity == null) {
            mTextSensorRelativeHumidity.setText(sensor_error);
        }
        if (mSensorRotationVector == null) {
            mTextSensorRotationVector.setText(sensor_error);
        }
        if (mSensorTemperature == null) {
            mTextSensorTemperature.setText(sensor_error);
        }
        if (mSensorGameRotationVector == null) {
            mTextSensorGameRotationVector.setText(sensor_error);
        }
        if (mSensorGeomagneticRotationVector == null) {
            mTextSensorGeomagneticRotationVector.setText(sensor_error);
        }


        //---END----------------------------------------------------

        btnStartTrcking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.main_started);
                activityType=1;

                startTracking();

            }
        });


                btnStopTracking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.activity_main);
                        stopTracking();
                    }
                });


                broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals(Constants.BROADCAST_DETECTED_ACTIVITY)) {
                            int type = intent.getIntExtra("type", -1);
                            int confidence = intent.getIntExtra("confidence", 0);
                            handleUserActivity(type, confidence);
                        }
                    }
                };



            }
        }, 4000);   //8 seconds from constants class

    }





    private void handleUserActivity(int type, int confidence) {
        String label = getString(R.string.activity_unknown);
       // int icon = R.drawable.ic_still;

        switch (type) {
            case DetectedActivity.IN_VEHICLE: {
                label = getString(R.string.activity_in_vehicle);
                Sauce.setType(1);
         //       icon = R.drawable.ic_driving;
                break;
            }
            case DetectedActivity.ON_BICYCLE: {
                label = getString(R.string.activity_on_bicycle);
                Sauce.setType(1);
           //     icon = R.drawable.ic_on_bicycle;
                break;
            }
            case DetectedActivity.ON_FOOT: {
                label = getString(R.string.activity_on_foot);
                Sauce.setType(0);
             //   icon = R.drawable.ic_walking;
                break;
            }
            case DetectedActivity.RUNNING: {
                label = getString(R.string.activity_running);
                Sauce.setType(1);
               // icon = R.drawable.ic_running;
                break;
            }
            case DetectedActivity.STILL: {
                label = getString(R.string.activity_still);
                Sauce.setType(0);
                break;
            }
            case DetectedActivity.TILTING: {
                label = getString(R.string.activity_tilting);
                Sauce.setType(0);
                //icon = R.drawable.ic_tilting;
                break;
            }
            case DetectedActivity.WALKING: {
                label = getString(R.string.activity_walking);
                Sauce.setType(1);
               // icon = R.drawable.ic_walking;
                break;
            }
            case DetectedActivity.UNKNOWN: {
                label = getString(R.string.activity_unknown);
                Sauce.setType(0);
                break;
            }
        }

        Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

        if (confidence > Constants.CONFIDENCE) {
            txtActivity.setText(label);
            txtConfidence.setText("Confidence: " + confidence);
           // imgActivity.setImageResource(icon);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(Constants.BROADCAST_DETECTED_ACTIVITY));
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void startTracking() {
        Intent intent = new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class);
        startService(intent);
    }

    private void stopTracking() {
        Intent intent = new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class);
        stopService(intent);
    }

    @Override
    protected void onStart () {
        super.onStart();

        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onPause().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL)
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAmbientTemperature != null) {
            mSensorManager.registerListener(this, mSensorAmbientTemperature,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGravity != null) {
            mSensorManager.registerListener(this, mSensorGravity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGyroscope != null) {
            mSensorManager.registerListener(this, mSensorGyroscope,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLinearAcceleration != null) {
            mSensorManager.registerListener(this, mSensorLinearAcceleration,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagneticField != null) {
            mSensorManager.registerListener(this, mSensorMagneticField,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorOrientation != null) {
            mSensorManager.registerListener(this, mSensorOrientation,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorRelativeHumidity != null) {
            mSensorManager.registerListener(this, mSensorRelativeHumidity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorRotationVector != null) {
            mSensorManager.registerListener(this, mSensorRotationVector,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorTemperature != null) {
            mSensorManager.registerListener(this, mSensorTemperature,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGameRotationVector != null) {
            mSensorManager.registerListener(this, mSensorGameRotationVector,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGeomagneticRotationVector != null) {
            mSensorManager.registerListener(this, mSensorGeomagneticRotationVector,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop () {
        super.onStop();

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is paused.
        mSensorManager.unregisterListener(this);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        LockTheLost lockTheLost= new LockTheLost();
        return lockTheLost.dispatchKeyEvent(event);
       // return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onSensorChanged (SensorEvent sensorEvent){


        // The sensor type (as defined in the Sensor class).
        int sensorType = sensorEvent.sensor.getType();

        // The new data value of the sensor.  Both the light and proximity
        // sensors report one value at a time, which is always the first
        // element in the values array.
        float currentValue = sensorEvent.values[0];
        float currentValueY = sensorEvent.values[1];
        float currentValueZ = sensorEvent.values[2];
        ///
        switch (sensorType) {
            // Event came from the light sensor.
            case Sensor.TYPE_LIGHT:
                // Set the light sensor text view to the light sensor string
                // from the resources, with the placeholder filled in.
                mTextSensorLight.setText(getResources().getString(
                        R.string.label_light, currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorProximity.setText(getResources().getString(
                        R.string.label_proximity, currentValue));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.


                //-----------/////////calling fall detection in sauce method /////////////////////////////////////////////////////////

                Sauce sauce = new Sauce();
                sauce.setAccX(currentValue);
                sauce.setAccY(currentValueY);
                sauce.setAccZ(currentValueZ);
                sauce.fallDetection();


                ////////////////////////////////////////////////////////''




                mTextSensorAccelerometer.setText(getResources().getString(
                        R.string.label_accelerometer, currentValue));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorAmbientTemperature.setText(getResources().getString(
                        R.string.label_ambientTemperature, currentValue));
                break;
            case Sensor.TYPE_GRAVITY:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorGravity.setText(getResources().getString(
                        R.string.label_gravity, currentValue));
                break;
            case Sensor.TYPE_GYROSCOPE:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                ////////////////////////////////
                Sauce sauce1 = new Sauce();
                sauce1.setGyroX(currentValue);
                sauce1.setGyroY(currentValueY);
                sauce1.setGyroZ(currentValueZ);
                ///////////////////////////////////
                mTextSensorGyroscope.setText(getResources().getString(
                        R.string.label_gyroscope, currentValue));
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorLinearAcceleration.setText(getResources().getString(
                        R.string.label_linearAcceleration, currentValue));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorMagneticField.setText(getResources().getString(
                        R.string.label_magneticField, currentValue));
                break;
            //////////////////////////////////////////////////////////////////////////////////
            case Sensor.TYPE_ORIENTATION:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorOrientation.setText(getResources().getString(
                        R.string.label_orientation, currentValue));
                break;
            //////////////////////////////////////////////////////////////////////////////////
            case Sensor.TYPE_PRESSURE:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorPressure.setText(getResources().getString(
                        R.string.label_pressure, currentValue));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorRelativeHumidity.setText(getResources().getString(
                        R.string.label_relativeHumidity, currentValue));
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorRotationVector.setText(getResources().getString(
                        R.string.label_rotationVector, currentValue));
                break;
            case Sensor.TYPE_TEMPERATURE:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorTemperature.setText(getResources().getString(
                        R.string.label_temperature, currentValue));
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorGameRotationVector.setText(getResources().getString(
                        R.string.label_gameRotationVector, currentValue));
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                // Set the proximity sensor text view to the light sensor
                // string from the resources, with the placeholder filled in.
                mTextSensorGeomagneticRotationVector.setText(getResources().getString(
                        R.string.label_geomagneticRotationVector, currentValue));
                break;


            default:
                // do nothing
        }
    }

    /**
     * Abstract method in SensorEventListener.  It must be implemented, but is
     * unused in this app.
     */
    @Override
    public void onAccuracyChanged (Sensor sensor,int i){
    }



}
