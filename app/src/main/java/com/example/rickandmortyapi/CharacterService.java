package com.example.rickandmortyapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface CharacterService {

    String BASE_URL = "https://rickandmortyapi.com/api/";

    @GET ("character/{number}")
    Call<Character> getCharacterByNumber (@Path("number") String number);
}
