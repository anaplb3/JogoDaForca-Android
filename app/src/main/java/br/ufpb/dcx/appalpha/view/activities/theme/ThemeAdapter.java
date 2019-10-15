package br.ufpb.dcx.appalpha.view.activities.theme;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
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
        private Context activityContext;

        public ThemeAdapter(List<Theme> themes, Context context) {
                this.themes = themes;
                this.activityContext = context;
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
                holder.themeImageLeft.setImageDrawable(themes.get(position).getImageUrl() != null ? ContextCompat.getDrawable(activityContext, Integer.parseInt(themes.get(position).getImageUrl())) : null);

//                Glide.with(activityContext)
//                        .load(urlImages.get("SUA URL"))
//                        .asBitmap()
//                        .listener(new RequestListener<String, Bitmap>() {
//                                @Override
//                                public boolean onException(Exception e, String model, com.bumptech.glide.request.target.Target<Bitmap> target, boolean isFirstResource) {
//                                        // AÇÕES A SE EXECUTAR CASO HOUVER ERRO
//                                        return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Bitmap resource, String model, com.bumptech.glide.request.target.Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                                        // AÇÕES A SE EXECUTAR QUANDO FOR CARREGADA A IMAGEM
//                                        return false;
//                                }
//                        }).into(SUA IMAGEVIEW);
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
