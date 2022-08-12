package sg.edu.rp.c346.id21021785.my_info_mobile_data_usage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvMobileData;
    AsyncHttpClient client;
    //ArrayAdapter<MobileData> aaMobileData;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMobileData = findViewById(R.id.lvMobileData);
        client = new AsyncHttpClient();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<MobileData> alMobileData = new ArrayList<MobileData>();

        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f", new JsonHttpResponseHandler() {

            String quarter;
            String volume;
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("onsuccess", "onSuccess");

                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject jsonObResult = response.getJSONObject("result");
                    JSONArray jsonRecordsArray = jsonObResult.getJSONArray("records");
                    for(int i = 0; i < jsonRecordsArray.length(); i++) {
                        JSONObject jsonObjRecords = jsonRecordsArray.getJSONObject(i);
                        quarter = jsonObjRecords.getString("quarter");
                        volume = jsonObjRecords.getString("volume_of_mobile_data");
                        MobileData mobileData = new MobileData(quarter, volume);
                        alMobileData.add(mobileData);
                    }
                }
                catch(JSONException e){

                }
                adapter = new CustomAdapter(MainActivity.this, R.layout.row, alMobileData);
                lvMobileData.setAdapter(adapter);
            }

            /*@Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("onsuccess", "onSuccess2");

            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);

                Log.d("onsuccess", "onSuccess3");
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d("fail", "fail1");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("fail", "fail2");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("fail", "fail13");
            }*/
        });
    }
}