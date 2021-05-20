package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private static final int fragmentoProgress = 5;

    /**
     * Constructor
     * @param tv
     * @param  bar
     */
    SimpleAsyncTask (TextView tv, ProgressBar bar){
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(bar);
    }//fin del constructor

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int milli = number * 400;
        int chunkSize = milli / fragmentoProgress;

        for (int i = 0; i <fragmentoProgress; i++){
            try {
                Thread.sleep(chunkSize);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100) / fragmentoProgress);
        }

        return "Awake after sleeping for " + milli + " milliseconds";
    }//fin del método doInBackground

    /**
     * Método onPostExecute
     * @param result
     */
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }//fin del método onPostExecute

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
    }
}//fin de la clase SimpleAsyncTask
