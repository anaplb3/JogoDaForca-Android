package br.dcx.appalpha.control.api;

import java.util.List;

import br.dcx.appalpha.model.bean.Challenge;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChallengeService{
    void find(Long id);

    void insert();

    void update();

    void delete(Long id);

    @GET("challenges")
    Call<List<Challenge>> findAll();

    void findPage(
        int page,
        int linesPerPage,
        String orderBy,
        String direction
    );
}