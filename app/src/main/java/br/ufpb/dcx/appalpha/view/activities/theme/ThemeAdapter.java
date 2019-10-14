package br.ufpb.dcx.appalpha.view.activities.theme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufpb.dcx.appalpha.R;

import java.util.List;

import br.ufpb.dcx.appalpha.model.bean.Theme;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
        private List<Theme> themes;
        private String  TAG = "ThemeListAdapter";

        public ThemeAdapter(List<Theme> themes) {
                this.themes = themes;

        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_theme, parent, false);
                return new ViewHolder(v);
        }

        public int getItemCount(){
                return themes.size();
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
                holder.themeNameLeft.setText(themes.get(position).getName());
        }

        class ViewHolder extends RecyclerView.ViewHolder{
                ImageView themeImageLeft;
                TextView themeNameLeft;

                public ViewHolder(View itemView) {
                        super(itemView);
                        themeImageLeft = itemView.findViewById(R.id.img_left);
                        themeNameLeft = itemView.findViewById(R.id.tv_theme_name_left);
                }

        }

}
