package my.jsonparsing;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import my.jsonparsing.models.MovieModel;

public class MainActivity1 extends AppCompatActivity {

    TextView tvData;
    private ListView lvMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley);

      tvData =(TextView)findViewById(R.id.jsondata);




        Button btnhit = (Button) findViewById(R.id.btnHit);
        btnhit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt");
                        new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
            }
        });


    }


   public class JSONTask extends AsyncTask<String, String,String>{

       @Override
       protected String doInBackground(String... params) {



           HttpURLConnection connection = null;
           BufferedReader reader = null;

           try {
               URL url = new URL(params[0]);
               connection = (HttpURLConnection) url.openConnection();
               connection.connect();

               InputStream stream = connection.getInputStream();
               reader = new BufferedReader(new InputStreamReader(stream));
               StringBuffer buffer = new StringBuffer();

               String line = "";
               while ((line = reader.readLine()) != null) {
                   buffer.append(line);
               }

               String finalJson = buffer.toString();


               JSONObject parentObject = new JSONObject();
             // JSONObject fina = parentObject.getJSONObject(0);
//               //JSONArray parentArray = parentObject.getJSONArray("movies");
               int year = parentObject.getInt("year");
               String name = parentObject.getString("bbtitle");
//
//
//               //StringBuffer finalBufferData = new StringBuffer();
//
//
//
//

//

                return String.valueOf(buffer.append(year +" - "+name));
               //return finalBufferData.toString();



               //....................The complete JSON..............................................

              // return finalJson.toString();

           } catch (MalformedURLException e) {

           } catch (IOException e) {
               e.printStackTrace();
           } catch (JSONException e) {
               e.printStackTrace();
           } finally {
               if ((connection != null)){
                   connection.disconnect();
               }

               try {
                   if (connection != null){
                       reader.close();
                   }

               } catch (IOException e) {
                   e.printStackTrace();
               }
           }


           return null;

       }


       protected void onPostExecute (String result) {
           super.onPostExecute(result);
           tvData.setText(result);




       }
   }






}
