package com.hoyath.pods;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.bluetooth.BluetoothProfile.GATT;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        BluetoothManager myBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter mBluetoothAdapter = myBluetoothManager.getAdapter();
        mBluetoothAdapter.enable();
        Set s = mBluetoothAdapter.getBondedDevices();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            BluetoothDevice d = (BluetoothDevice) it.next();
            System.out.println("device is " + d.getName());

            Toast.makeText(this, d.getName() + "--" + d.ACTION_ACL_CONNECTED, Toast.LENGTH_SHORT).show();

        }
//alert
*/
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(BTReceiver, filter1);

    }
    private final BroadcastReceiver BTReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch(state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(getApplicationContext(),"STATE_OFF", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(getApplicationContext(),"STATE_Truning_off", Toast.LENGTH_SHORT).show();

                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(getApplicationContext(),"on", Toast.LENGTH_SHORT).show();

                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(getApplicationContext(),"turning on", Toast.LENGTH_SHORT).show();
                        break;
                }
                if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(action))
                 Toast.makeText(getApplicationContext(),"soemthing os going on on", Toast.LENGTH_SHORT).show();


            }
        }
    };
}


