package androidapp.batru.dubaothoitiet;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.SingletonClass;
import model.VolleySingleton;

public class MainActivity extends AppCompatActivity {

    private static final String VolleyTAG = "MYTAG";

    private static final String url = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String apiLink = "&appid=50c4705f25a93cb530a067bd324c4c8d";
    private String cityName = "Ha%20Noi";

    private TextView tvCity, tvCurrentMain, tvHumidity, tvCurrentDeg;
    private TextView tvWindSpeed, tvSunrise, tvSunset, tvLastUpdated;
    private ImageView imgIcon;

    // Volley

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
    }

    private void anhXa() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        tvCity = (TextView) findViewById(R.id.cityText);
        tvCurrentMain = (TextView) findViewById(R.id.tvCurrentMain);
        tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        tvCurrentDeg = (TextView) findViewById(R.id.tvCurrentDeg);
        tvWindSpeed = (TextView) findViewById(R.id.tvWindSpeed);
        tvSunrise = (TextView) findViewById(R.id.tvSunrise);
        tvSunset = (TextView) findViewById(R.id.tvSunset);
        tvLastUpdated = (TextView) findViewById(R.id.tvLastUpdated);
        imgIcon = (ImageView) findViewById(R.id.currentIconImage);

    }

    private void getJsonData() {
        String urlLink = url + cityName + apiLink;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
               urlLink , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ganDuLieu(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        jsonObjectRequest.setTag(VolleyTAG);
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void ganDuLieu(JSONObject respone) {
        if (respone == null)
            return;
        try {
            JSONObject windObj = respone.getJSONObject("wind");
            float speed = (float) windObj.getDouble("speed");
            tvWindSpeed.setText(speed + " m/s");

            JSONArray weatherArray = respone.getJSONArray("weather");
            JSONObject weatherObj = weatherArray.getJSONObject(0);
            String currentMain = weatherObj.getString("main");
            tvCurrentMain.setText(currentMain);

            String iconString = weatherObj.getString("icon");
            String iconRaw = SingletonClass.getInstance().ChangeStringIcon(iconString);
            int iconId = SingletonClass.getInstance().getImageId(this,iconRaw);
            imgIcon.setImageResource(iconId);

            String city = respone.getString("name");
            JSONObject sysObj = respone.getJSONObject("sys");
            String country = sysObj.getString("country");
            tvCity.setText(city + ", " + country);

            String timeLastUpdated = SingletonClass.getInstance().ConvertUnixToTime(respone.getInt("dt"), "h:mm a");
            String timeSunrise = SingletonClass.getInstance().ConvertUnixToTime(sysObj.getInt("sunrise"), "h:mm a");
            String timeSunset = SingletonClass.getInstance().ConvertUnixToTime(sysObj.getInt("sunset"), "h:mm a");
            tvSunrise.setText(timeSunrise);
            tvSunset.setText(timeSunset);
            tvLastUpdated.setText(timeLastUpdated);

            JSONObject mainObj = respone.getJSONObject("main");
            String humidity = mainObj.getString("humidity");
            tvHumidity.setText(humidity + "%");

        } catch (JSONException e) {
            Log.d("ERROR", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_setting) {
            getJsonData();
            //Toast.makeText(this, "HELLO SETTING", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    //region Luu sharedPreferences
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    //endregion
}
