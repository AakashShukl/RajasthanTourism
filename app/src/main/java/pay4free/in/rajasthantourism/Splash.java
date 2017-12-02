package pay4free.in.rajasthantourism;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;






        public class Splash extends AppCompatActivity {

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_splash);

                new initializeApp().execute();
            }

            private class initializeApp extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    startActivity(new Intent(Splash.this, MainActivity.class));
                }
            }
        }



