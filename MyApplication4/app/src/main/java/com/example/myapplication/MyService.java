package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private  final static String TAG = MyService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Start Service");
    }

    public static final String EXTRA_TEXT = "EXTRA_TEXT";
    String name;
    int print_number;
    int count_number;
    private  Thread countNumber = new CountNum();
    private  Thread printNumber = new PrintNum();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //printNumber.start();
        //Log.d(TAG, intent.getStringExtra(EXTRA_TEXT));
        if(intent.hasExtra("printnumselected")) {
            if (intent != null && intent.hasExtra(EXTRA_TEXT)) {
                name = intent.getStringExtra(EXTRA_TEXT);
                print_number = intent.getIntExtra("selected", 1);
                if (!printNumber.isAlive()) {
                    printNumber = new PrintNum();
                    printNumber.start();
                }
            } else if (!intent.hasExtra(EXTRA_TEXT) && printNumber.isAlive()) {
                printNumber.interrupt();
            }
        }

        if(intent.hasExtra("countstart")){
            int iscount =intent.getIntExtra("countstart",1);
            count_number=intent.getIntExtra("cnt_selected",1);
            if(!countNumber.isAlive() && iscount==1){
                countNumber = new CountNum();
                countNumber.start();
            }
            else if(iscount==0){
                countNumber.interrupt();
            }
        }
        return Service.START_STICKY;
    }




    @Override
    public void onDestroy() {
        Log.d(TAG, "Stop Service");
        printNumber.interrupt();
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    class CountNum extends Thread{
        int count=0;
        @Override
        public void run() {
            try{
                while(true){
                    Thread.sleep(count_number);
                    Log.d(TAG, "Count = "+count+", Interval = "+count_number+"ms");
                    count++;
                }
            }catch (Exception e){
                Log.e(TAG,e.getStackTrace().toString());

            }
        }
    }

    class PrintNum extends Thread{
        @Override
        public void run() {
            try{
                while(true){
                    Thread.sleep(print_number);
                    Log.d(TAG, "Content = "+name+", Interval = "+print_number+"ms");
                }
            }catch (Exception e){
                Log.e(TAG,e.getStackTrace().toString());

            }
        }
    }
}


