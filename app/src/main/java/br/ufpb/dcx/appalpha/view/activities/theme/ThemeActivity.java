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
import br.ufpb.dcx.appalpha.control.service.ThemeService;
import br.ufpb.dcx.appalpha.control.util.ScreenUtil;
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
    private ThemeService themeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        getLayoutInflater().inflate(R.layout.activity_tema, null);

        recyclerView = findViewById(R.id.rcThemes);
        layManager = new GridLayoutManager(getApplicationContext(), 2);
        this.themeService = ThemeService.getInstance(getApplicationContext());
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
        //getAllChallengesFromService();
        ScreenUtil.getInstance().unlockScreenTouch(this);
        activity = this;
    }

    public void addDefaultThemes(){
        this.themes = this.themeService.getAll();
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
            ScreenUtil.getInstance().lockScreenTouch(ThemeActivity.activity);
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
