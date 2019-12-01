package br.ufpb.dcx.appalpha.control.api;

import br.ufpb.dcx.appalpha.control.service.interfaces.ChallengeApiService;
import br.ufpb.dcx.appalpha.control.service.interfaces.ThemesApiServiceInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private String BASE_URL = "https://educapi.herokuapp.com/";

    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public ThemesApiServiceInterface contextService(){
        return retrofit.create(ThemesApiServiceInterface.class);
    }

    public ChallengeApiService challengeService(){
        return retrofit.create(ChallengeApiService.class);
    }

}