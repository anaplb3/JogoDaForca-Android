package br.ufpb.dcx.appalpha.view.activities.theme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.ChallengeFacade;
import br.ufpb.dcx.appalpha.control.util.ScreenUtils;
import br.ufpb.dcx.appalpha.control.util.SomUtil;
import br.ufpb.dcx.appalpha.control.api.RetrofitInitializer;
import br.ufpb.dcx.appalpha.model.bean.Challenge;
import br.ufpb.dcx.appalpha.model.bean.Theme;
import br.ufpb.dcx.appalpha.view.activities.ForcaActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThemeActivity extends AppCompatActivity {
    private static final String TAG = "ThemeActivity";
    private GridLayoutManager layManager;
    private RecyclerView recyclerView;
    private List<Theme> themes = new ArrayList<>();
    protected static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        getLayoutInflater().inflate(R.layout.activity_tema, null);

        recyclerView = findViewById(R.id.rcThemes);
        layManager = new GridLayoutManager(getApplicationContext(), 2);
    }

    public void fillRecycleView(List<Theme> themes){
        recyclerView.setLayoutManager(layManager);
        recyclerView.setAdapter(new ThemeAdapter(themes, getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDefaultThemes();
        fillRecycleView(themes);
        getAllChallengesFromService();
        activity = this;
    }

    public void addDefaultThemes(){

        Theme comida = new Theme("comida",  Integer.toString(R.drawable.comida), Integer.toString(R.raw.comida), null);
        comida.getChallenges().addAll(Arrays.asList(
                new Challenge("arroz", null, Integer.toString(R.raw.arroz), null, Integer.toString(R.drawable.arroz)),
                new Challenge("peixe", null, Integer.toString(R.raw.peixe), null, Integer.toString(R.drawable.peixe)),
                new Challenge("batata", null, Integer.toString(R.raw.batata), null, Integer.toString(R.drawable.batata)),
                new Challenge("carne", null, Integer.toString(R.raw.carne), null, Integer.toString(R.drawable.carne)),
                new Challenge("macarrao", null, Integer.toString(R.raw.macarrao), null, Integer.toString(R.drawable.macarrao)),
                new Challenge("bolo", null, Integer.toString(R.raw.bolo), null, Integer.toString(R.drawable.bolo)),
                new Challenge("carne", null, Integer.toString(R.raw.carne), null, Integer.toString(R.drawable.carne)),
                new Challenge("ovo", null, Integer.toString(R.raw.ovo), null, Integer.toString(R.drawable.ovo))));
        themes.add(comida);

        Theme cidade = new Theme("cidade",  Integer.toString(R.drawable.cidade), Integer.toString(R.raw.cidade), null);
        cidade.getChallenges().addAll(Arrays.asList(
                new Challenge("aeroporto", null, Integer.toString(R.raw.aeroporto), null, Integer.toString(R.drawable.aeroporto)),
                new Challenge("hospital", null, Integer.toString(R.raw.hospital), null, Integer.toString(R.drawable.hospital)),
                new Challenge("igreja", null, Integer.toString(R.raw.igreja), null, Integer.toString(R.drawable.igreja)),
                new Challenge("museu", null, Integer.toString(R.raw.museu), null, Integer.toString(R.drawable.museu)),
                new Challenge("shopping", null, Integer.toString(R.raw.shopping), null, Integer.toString(R.drawable.shopping))));
        themes.add(cidade);

        Theme cores = new Theme("cores",  Integer.toString(R.drawable.cores), Integer.toString(R.raw.cores), null);
        cores.getChallenges().addAll(Arrays.asList(
                new Challenge("preto", null, Integer.toString(R.raw.preto), null, Integer.toString(R.drawable.preto)),
                new Challenge("rosa", null, Integer.toString(R.raw.rosa), null, Integer.toString(R.drawable.rosa)),
                new Challenge("vermelho", null, Integer.toString(R.raw.vermelho), null, Integer.toString(R.drawable.vermelho)),
                new Challenge("verde", null, Integer.toString(R.raw.verde), null, Integer.toString(R.drawable.verde)),
                new Challenge("marrom", null, Integer.toString(R.raw.marrom), null, Integer.toString(R.drawable.marrom))));
        themes.add(cores);

        Theme cozinha = new Theme("cozinha",  Integer.toString(R.drawable.cozinha), Integer.toString(R.raw.cozinha), null);
        cozinha.getChallenges().addAll(Arrays.asList(
                new Challenge("faca", null, Integer.toString(R.raw.faca), null, Integer.toString(R.drawable.faca)),
                new Challenge("colher", null, Integer.toString(R.raw.colher), null, Integer.toString(R.drawable.colher)),
                new Challenge("garfo", null, Integer.toString(R.raw.garfo), null, Integer.toString(R.drawable.garfo)),
                new Challenge("fogao", null, Integer.toString(R.raw.fogao), null, Integer.toString(R.drawable.fogao)),
                new Challenge("mesa", null, Integer.toString(R.raw.mesa), null, Integer.toString(R.drawable.mesa))));
        themes.add(cozinha);

        Theme natureza = new Theme("natureza",  Integer.toString(R.drawable.natureza), Integer.toString(R.raw.natureza), null);
        natureza.getChallenges().addAll(Arrays.asList(
                new Challenge("vaca", null, Integer.toString(R.raw.vaca), null, Integer.toString(R.drawable.vaca)),
                new Challenge("galo", null, Integer.toString(R.raw.galo), null, Integer.toString(R.drawable.galo)),
                new Challenge("bode", null, Integer.toString(R.raw.bode), null, Integer.toString(R.drawable.bode)),
                new Challenge("abelha", null, Integer.toString(R.raw.abelha), null, Integer.toString(R.drawable.abelha)),
                new Challenge("flor", null, Integer.toString(R.raw.flor), null, Integer.toString(R.drawable.flor))));
        themes.add(natureza);

        Theme frutas = new Theme("frutas",  Integer.toString(R.drawable.frutas), Integer.toString(R.raw.frutas), null);
        frutas.getChallenges().addAll(Arrays.asList(
                new Challenge("banana", null, Integer.toString(R.raw.banana), null, Integer.toString(R.drawable.banana)),
                new Challenge("abacate", null, Integer.toString(R.raw.abacate), null, Integer.toString(R.drawable.abacate)),
                new Challenge("abacaxi", null, Integer.toString(R.raw.abacaxi), null, Integer.toString(R.drawable.abacaxi)),
                new Challenge("manga", null, Integer.toString(R.raw.manga), null, Integer.toString(R.drawable.manga)),
                new Challenge("melancia", null, Integer.toString(R.raw.melancia), null, Integer.toString(R.drawable.melancia))));
        themes.add(frutas);

    }

    public void getAllChallengesFromService(){
        Call call = new RetrofitInitializer().contextService().findAll();
        call.enqueue(new Callback<List<Theme>>() {
            @Override
            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
                List<Theme> reponseBody = response.body();
                Log.i(TAG, ""+reponseBody.size());
                for(Theme t : reponseBody){
                    themes.add(t);
                    fillRecycleView(themes);
                }
            }

            @Override
            public void onFailure(Call<List<Theme>> call, Throwable t) {
                Log.e(TAG, "Erro ao recuperar temas: "+t.getMessage());
            }
        });
    }

    public static void OnClickListener(OnClickListener hook){
        Theme selectedTheme = hook.onItemClicked();
        Log.i(TAG, "Theme " + selectedTheme.getName() + " Clicked!");
        if(selectedTheme.getChallenges() != null && selectedTheme.getChallenges().size() > 0) {
            ThemeActivity.goToSelectedChallenge(selectedTheme);
            ScreenUtils.getInstance().lockScreenTouch(ThemeActivity.activity);
        }else{
            Toast.makeText(ThemeActivity.activity, "O tema selecionado não possui desafios, tente outro tema.", Toast.LENGTH_LONG).show();
        }
    }

    interface OnClickListener{
        Theme onItemClicked();
    }

    private static void setChallengesInFacade(Theme selectedTheme){
        ChallengeFacade.getInstance().init(selectedTheme.getChallenges(), selectedTheme);
    }

    private static void goToSelectedChallenge(Theme selectedTheme){
        setChallengesInFacade(selectedTheme);
        playThemeSong(selectedTheme);

        Intent intent = new Intent(ThemeActivity.activity, ForcaActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() { //Wait the song end to start new activity
            @Override
            public void run() {
                ThemeActivity.activity.startActivity(intent);

            }
        }, SomUtil.getInstance().getDuracao());

    }

    private static void playThemeSong(Theme selectedTheme){
        SomUtil.getInstance().playSound(ThemeActivity.activity, Integer.parseInt(selectedTheme.getSoundUrl()));
    }

    public void botaoEscolha(ImageView img_button) {




    }

}
