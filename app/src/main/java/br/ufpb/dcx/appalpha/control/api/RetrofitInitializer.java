package br.ufpb.dcx.appalpha.control.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private String BASE_URL = "https://educapi.herokuapp.com/";

    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public ContextService contextService(){
        return retrofit.create(ContextService.class);
    }

    public ChallengeService challengeService(){
        return retrofit.create(ChallengeService.class);
    }

}