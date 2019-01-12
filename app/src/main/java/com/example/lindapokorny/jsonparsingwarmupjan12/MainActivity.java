package com.example.lindapokorny.jsonparsingwarmupjan12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL_DOG_CEO = "https://dog.ceo";
    private ImageView dogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dogView = findViewById(R.id.dogView);

        //https:dog.ceo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //option Command N will help you refactor your inline references
       DogApi api = retrofit.create(DogApi.class);
       Call<RandomDog> puppyCall = api.getRandomDog();
       //Enqueue will run the line after the Enqueue network call is done.
        puppyCall.enqueue(new Callback<RandomDog>() {
           @Override
           public void onResponse(Call<RandomDog> call, Response<RandomDog> response) {
               RandomDog responseDog = response.body();
            Log.d("woohooo", "onResponse: " + responseDog.getDogURL());
               Picasso.get()
                       .load(responseDog.getDogURL())
                       .into(dogView);
           }

           @Override
           public void onFailure(Call<RandomDog> call, Throwable t) {
            Log.d("boohoo", "onFailure: " + t.getMessage());


           }
       });
//when does your OnFailure method get called  --> You have to know what Status codes mean (200 ok you will hit onResponse.)
//if you're hitting 400 - 500 it calls your onFailure
    }
}
        /*WarmUp

          //public static final String TAG = "JSON NEW ACTIVITY";
        in onCreate():
        JSONObject newObject = new JSONObject();
        try {
            newObject.put("Name", "Linda")
                    .put("Age", "28");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "OnCreate(): " + newObject.toString());
    }
*/
