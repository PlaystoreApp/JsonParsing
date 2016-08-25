package my.jsonparsing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SVP on 15-Jun-16.
 */
public class MainActivity2 extends AppCompatActivity {
    private ProgressDialog pDialog;
    TextView tv;


    Button get_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley);


        get_button = (Button) findViewById(R.id.btnHit);
        get_button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {


                                              pDialog = new ProgressDialog(MainActivity2.this);
                                              pDialog.setMessage("Loading");
                                              pDialog.show();


                                              RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                              String url = "http://.php?";
                                              StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                      new Response.Listener<String>() {
                                                          @Override
                                                          public void onResponse(String response) {
                                                             // String data = "0";
                                                              // Display the first 500 characters of the response string.
                                                              pDialog.dismiss();

                                                              try{
                                                                  JSONObject jsonObject = new JSONObject(response);
                                                                  String apiaccesstoken = jsonObject.getString("apiaccesstoken");
                                                                  Toast.makeText(getApplicationContext(),""+apiaccesstoken,Toast.LENGTH_SHORT).show();
                                                              }catch (Exception e)
                                                              {

                                                              }
                                                           //   Toast.makeText(getApplicationContext(),""+data,Toast.LENGTH_SHORT).show();


                                                             tv = (TextView) findViewById(R.id.jsondata);



                                                          }
                                                      }, new Response.ErrorListener() {
                                                  @Override
                                                  public void onErrorResponse(VolleyError error) {
                                                      // mTextView.setText("That didn't work!");
                                                      pDialog.dismiss();
                                                      Toast.makeText(getApplicationContext(),"error"+error,Toast.LENGTH_SHORT).show();
                                                  }
                                              }){
//
//                                                  @Override
//                                                  protected Map<String, String> getParams() throws AuthFailureError {
//                                                      Map<String,String> params = new HashMap<>();
//                                                      params.put("api_m", "api_init");
//                                                      params.put("clientname", "Tutu");
//                                                      params.put("clientversion","2.0");
//                                                      params.put("platformname","iphone");
//                                                      params.put("platformversion","7");
//                                                      params.put("uniqueid","ponnu7574");
//                                                      return params;
//                                                  }
//


                                              };
// Add the request to the RequestQueue.
                                              queue.add(stringRequest);
                                              tv.setText(stringRequest.toString());



                                          }
                                      }
        );
    }

//            private class CustomHttpClient extends AsyncTask<String, String, String> {
//
//                @Override
//                protected void onPreExecute() {
//                    super.onPreExecute();
//                    // Showing progress dialog
//                    pDialog = new ProgressDialog(MainActivity2.this);
//                    pDialog.setMessage("Please wait...");
//                    pDialog.setCancelable(false);
//                    pDialog.show();
//
//                }
//
//
//             @Override
//             protected String doInBackground(String... params) {
//               //  String directorName = null;
//
//                 String url = params[0];
//
//                 Log.d("Url",url);
//
//                 ServiceHandler sh = new ServiceHandler();
//                 // Making a request to url and getting response
//                 String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST);
//
//                 Log.d("Response: ", "> " + jsonStr);
//                 if (jsonStr != null) {
//                     try {
//                         JSONObject jsonObj = new JSONObject(jsonStr);
//
//                         // Getting JSON
//                         String apiaccesstoken = jsonObj.getString("apiaccesstoken");
//
//                         Log.d("ApiAccessToken",apiaccesstoken);
//
//                         Toast.makeText(MainActivity2.this,apiaccesstoken,Toast.LENGTH_SHORT).show();
//
//
//
//
//                     } catch (JSONException e) {
//                         e.printStackTrace();
//                     }
//                 } else {
//                     Log.e("ServiceHandler", "Couldn't get any data from the url");
//                 }
//
//                 return null;
//             }
//
//                @Override
//                protected void onPostExecute(String result) {
//                    // TODO Auto-generated method stub
//                    super.onPostExecute(result);
//
//
//                }
//
//
//    }

}






