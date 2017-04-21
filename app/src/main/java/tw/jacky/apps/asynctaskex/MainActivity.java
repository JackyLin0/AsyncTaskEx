package tw.jacky.apps.asynctaskex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String url ;
    ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imgview);
        url = "http://img.hypesphere.com/2015-09-24-201724-73.jpg";

        new GetImg().execute(url,url,url);
     }



    class GetImg extends AsyncTask<String,Integer,Bitmap>
    {
        Bitmap bitmap;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            int progress = 0;
             for (String urlStr : params) {
                try {
                    URL url = new URL(urlStr);
                    Log.v("Jacky",urlStr);
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                     return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
             img.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
