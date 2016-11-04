package my.jsonparsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import my.jsonparsing.models.MovieModel;

/**
 * Created by user-1 on 19-Jul-16.
 */

public class volley extends AppCompatActivity {

    TextView tvData;
    Button Hit;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley);


        final String link = ("");

        final String link1 = ("");


        tvData = (TextView) findViewById(R.id.jsondata);
        requestQueue = Volley.newRequestQueue(this);
        Hit = (Button) findViewById(R.id.btnHit);

        Hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        link1, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //Log.d(TAG, response.toString());
                                // pDialog.hide();


                                try {

                                    //..............................link1....................Display all districts Array...............

                                    //JSONObject jsonObject = new JSONObject(String.valueOf(response));

                                    JSONArray districts = response.getJSONArray("districts");

                                    for (int i = 0; i < districts.length(); i++) {
                                        JSONObject jsonObject1 = districts.getJSONObject(i);

                                        String ac_name_en = jsonObject1.getString("ac_name_en");
                                        Toast.makeText(getApplicationContext(), "" + ac_name_en, Toast.LENGTH_SHORT).show();
                                        Log.d("Districts:", ac_name_en);
                                    }


//..............................link.......only object..............................................

                                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                   // JSONObject jsonObject = response.getJSONObject(link);
                                    String apiaccesstoken = jsonObject.getString("apiaccesstoken");
                                    JSONObject features = response.getJSONObject("features");
                                    int blogenabled = features.getInt("blogenabled");
                                   // Toast.makeText(getApplicationContext(),""+apiaccesstoken,Toast.LENGTH_SHORT).show();
                                   tvData.append("Token: "+apiaccesstoken+"  "+"\n"+"Features: "+blogenabled+"\n");


//...............................link3 ....first Array..............................................



                                } catch (Exception e) {


                                }


                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // VolleyLog.d(TAG, "Error: " + error.getMessage());
                        // hide the progress dialog
                        // pDialog.hide();
                    }
                });


                requestQueue.add(jsonObjReq);


            }
        });


        //String tag_json_obj = "json_obj_req";

        //  String url = "http://api.androidhive.info/volley/person_object.json";

//        ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();


// Adding request to request queue


    }



}
