package my.jsonparsing;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user-1 on 25-Jul-16.
 */

public class Volley1 extends AppCompatActivity {

    TextView tvData;
    Button Hit;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley);

        final String link = "https://";

        requestQueue = Volley.newRequestQueue(this);
        tvData =(TextView)findViewById(R.id.jsondata);
        Hit = (Button)findViewById(R.id.btnHit);
        Hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, link, null,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {


                                    try {
                                        JSONObject totalresults = new JSONObject(String.valueOf(response));
                                        int total_results = totalresults.getInt("total_results");
                                        Log.d("total_results: ", String.valueOf(total_results));
                                        JSONArray filings = response.getJSONArray("filings");

                                       // JSONArray jsonarray = response.getJSONArray(String.valueOf(response));
                                       // JSONArray jsonarray = new JSONArray(link);
                                        //JSONObject jobj1 = new JSONObject(String.valueOf(response));
                                        for (int i = 0; i<filings.length(); i++){
                                            JSONObject jobj = filings.getJSONObject(i);
                                            int tax_prd = jobj.getInt("tax_prd");
                                            Toast.makeText(getApplicationContext(),""+tax_prd,Toast.LENGTH_SHORT).show();
                                            Log.d("Login ID:", String.valueOf(tax_prd));
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
