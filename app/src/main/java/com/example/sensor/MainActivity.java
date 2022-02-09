package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;

    private Range<Integer> xAxisRange;
    private Range<Integer> yAxisRange;
    private Range<Integer> zAxisRange;

    private TextView sensorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        sensorTxt = (TextView) findViewById(R.id.sensorTxt);

    }


    // event listener to sensor
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Toast.makeText(getApplicationContext(), "hello you", Toast.LENGTH_LONG).show();
            float[] values;

            values = event.values;

            horizontalRange(values);
            verticalRange(values);
            upDownRange(values);
            leftRotationRange(values);
            rightRotationRange(values);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void upDownRange(float[] values) {
        //upside-down range
        xAxisRange = Range.create(-1,1);
        yAxisRange = Range.create(-10,-8);
        zAxisRange = Range.create(2,5);
        if (xAxisRange.contains((int) values[0]) && yAxisRange.contains((int) values[1]) && zAxisRange.contains((int) values[2])){
            sensorTxt.setText("phone rotated upside-down");
        }

    }

    private void leftRotationRange(float[] values) {
        //left rotate range
        xAxisRange = Range.create(8,10);
        yAxisRange = Range.create(-1,1);
        zAxisRange = Range.create(2,5);
        if (xAxisRange.contains((int) values[0]) && yAxisRange.contains((int) values[1]) && zAxisRange.contains((int) values[2])){
            sensorTxt.setText("phone rotated left");
        }
    }

    private void verticalRange(float[] values) {
        //vertical range
        xAxisRange = Range.create(-1,1);
        yAxisRange = Range.create(8,10);
        zAxisRange = Range.create(2,5);
        if (xAxisRange.contains((int) values[0]) && yAxisRange.contains((int) values[1]) && zAxisRange.contains((int) values[2])){
            sensorTxt.setText("phone rotated vertically");
        }
    }

    private void rightRotationRange(float[] values) {
        //right range
        xAxisRange = Range.create(-10,-8);
        yAxisRange = Range.create(-1,1);
        zAxisRange = Range.create(2,5);
        if (xAxisRange.contains((int) values[0]) && yAxisRange.contains((int) values[1]) && zAxisRange.contains((int) values[2])){
            sensorTxt.setText("phone rotated right");
        }
    }

    private void horizontalRange(float[] values) {
        //horizontal range
        xAxisRange = Range.create(-1,1);
        yAxisRange = Range.create(-1,1);
        zAxisRange = Range.create(10,12);
        if (xAxisRange.contains((int) values[0]) && yAxisRange.contains((int) values[1]) && zAxisRange.contains((int) values[2])){
            sensorTxt.setText("phone set honrizontally");
        }
    }



    @Override
    protected void onResume() {
        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
          super.onResume();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (sensor != null ) {
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
}