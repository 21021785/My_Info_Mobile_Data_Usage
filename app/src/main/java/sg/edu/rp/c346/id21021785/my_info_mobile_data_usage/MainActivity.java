package sg.edu.rp.c346.id21021785.my_info_mobile_data_usage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

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
    Spinner spinnerYear;
    String year = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMobileData = findViewById(R.id.lvMobileData);
        client = new AsyncHttpClient();
        spinnerYear = findViewById(R.id.spinnerYear);

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        year = "All";
                        showListView();
                        break;
                    case 1:
                        year = "2004";
                        showListView();
                        break;
                    case 2:
                        year = "2005";
                        showListView();
                        break;
                    case 3:
                        year = "2006";
                        showListView();
                        break;
                    case 4:
                        year = "2007";
                        showListView();
                        break;
                    case 5:
                        year = "2008";
                        showListView();
                        break;
                    case 6:
                        year = "2009";
                        showListView();
                        break;
                    case 7:
                        year = "2010";
                        showListView();
                        break;
                    case 8:
                        year = "2011";
                        showListView();
                        break;
                    case 9:
                        year = "2012";
                        showListView();
                        break;
                    case 10:
                        year = "2013";
                        showListView();
                        break;
                    case 11:
                        year = "2014";
                        showListView();
                        break;
                    case 12:
                        year = "2015";
                        showListView();
                        break;
                    case 13:
                        year = "2016";
                        showListView();
                        break;
                    case 14:
                        year = "2017";
                        showListView();
                        break;
                    case 15:
                        year = "2018";
                        showListView();
                        break;
                    case 16:
                        year = "2019";
                        showListView();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        showListView();

    }

    public void showListView() {
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
                    for (int i = 0; i < jsonRecordsArray.length(); i++) {
                        JSONObject jsonObjRecords = jsonRecordsArray.getJSONObject(i);
                        quarter = jsonObjRecords.getString("quarter");
                        volume = jsonObjRecords.getString("volume_of_mobile_data");
                        MobileData mobileData = new MobileData(quarter, volume);
                        if (year.equalsIgnoreCase("All")) {
                            alMobileData.add(mobileData);
                        } else {
                            if (mobileData.getQuarter().startsWith(year)) {
                                alMobileData.add(mobileData);
                            }
                        }
                    }
                } catch (JSONException e) {

                }
                adapter = new CustomAdapter(MainActivity.this, R.layout.row, alMobileData);
                lvMobileData.setAdapter(adapter);
            }
        });
    }
}











//Alternative Solution (Requires Search Button)

/*package sg.edu.rp.c346.id21021785.my_info_mobile_data_usage;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Spinner;

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
    Spinner spinnerYear;
    Button btnSearch;
    String year = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMobileData = findViewById(R.id.lvMobileData);
        client = new AsyncHttpClient();
        btnSearch = findViewById(R.id.btnSearch);
        spinnerYear = findViewById(R.id.spinnerYear);
        btnSearch.performClick();

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        year = "All";
                        break;
                    case 1:
                        year = "2004";
                        break;
                    case 2:
                        year = "2005";
                        break;
                    case 3:
                        year = "2006";
                        break;
                    case 4:
                        year = "2007";
                        break;
                    case 5:
                        year = "2008";
                        break;
                    case 6:
                        year = "2009";
                        break;
                    case 7:
                        year = "2010";
                        break;
                    case 8:
                        year = "2011";
                        break;
                    case 9:
                        year = "2012";
                        break;
                    case 10:
                        year = "2013";
                        break;
                    case 11:
                        year = "2014";
                        break;
                    case 12:
                        year = "2015";
                        break;
                    case 13:
                        year = "2016";
                        break;
                    case 14:
                        year = "2017";
                        break;
                    case 15:
                        year = "2018";
                        break;
                    case 16:
                        year = "2019";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        *//*btnSearch.performClick();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                if (year.equalsIgnoreCase("All")) {
                                    alMobileData.add(mobileData);
                                } else {
                                    if (mobileData.getQuarter().startsWith(year)) {
                                        alMobileData.add(mobileData);
                                    }
                                }
                            }
                        }
                        catch(JSONException e){

                        }
                        adapter = new CustomAdapter(MainActivity.this, R.layout.row, alMobileData);
                        lvMobileData.setAdapter(adapter);
                    }*//*

            *//*@Override
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
            }*//*
                *//*});
            }*//*


        ;
    }
}*/



