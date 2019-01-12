package com.example.lindapokorny.jsonparsingwarmupjan12;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApi {
    String ENDPOINT_RANDOM_DOG = "api/breeds/image/random";

    @GET(ENDPOINT_RANDOM_DOG) //get method tells you what kind of call you want to make
    Call<RandomDog> getRandomDog(); //when i call this method, you're going to get the endpoint (or the API)
    //to go from an object to json its serializing; when you go from object to json its de-serializing
}
