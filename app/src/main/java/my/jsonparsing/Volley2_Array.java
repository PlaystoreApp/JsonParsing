package my.jsonparsing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by user-1 on 25-Jul-16.
 */

public class Volley2_Array extends AppCompatActivity {

    TextView tvData;
    Button Hit;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        final String link = "https://api.github.com/users/hadley/orgs";



        requestQueue = Volley.newRequestQueue(this);
        tvData =(TextView)findViewById(R.id.jsondata);
        Hit = (Button)findViewById(R.id.btnHit);
        Hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    JsonArrayRequest objectRequest = new JsonArrayRequest(link,
                            new Response.Listener<JSONArray>() {

                                @Override
                                public void onResponse(JSONArray response) {


                                    try {
                                        ListView listView = (ListView)findViewById(R.id.listMovies);
                                        ArrayAdapter adapter = new ArrayAdapter<String>(Volley2_Array.this,R.layout.row);
                                        listView.setAdapter(adapter);

                                        for (int i = 0; i<response.length(); i++){
                                            JSONObject jobj = response.getJSONObject(i);
                                            int id = jobj.getInt("id");
                                            Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_SHORT).show();
                                            Log.d("Login ID:", String.valueOf(id));
                                        }

                                    } catch (Exception e) {

                                    }
                                }






                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });


                    requestQueue.add(objectRequest);



            }
        });




    }
}
