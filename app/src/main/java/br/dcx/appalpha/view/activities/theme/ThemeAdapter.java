package br.dcx.appalpha.view.activities.theme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anaplb.appalpha.R;

import java.util.List;

import br.dcx.appalpha.model.bean.Challenge;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
        private List<Challenge> challenges;
        private String  TAG = "ThemeListAdapter";

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_theme, parent, false);
                return new ViewHolder(v);
        }

        public int getItemCount(){
                return challenges.size();
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
        }

        class ViewHolder extends RecyclerView.ViewHolder{
                ImageView themeImage;
                TextView themeName;

                public ViewHolder(View itemView) {
                        super(itemView);

                }

        }

}
