package br.ufpb.dcx.appalpha.view.activities.theme;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

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
                loadImage(themes.get(position).getImageUrl(), holder.themeImageLeft);
        }

        private void loadImage(String imageUrl, ImageView themeImageLeft){
            DiskCacheStrategy diskCacheStrategy = new DiskCacheStrategy() {
                @Override
                public boolean isDataCacheable(DataSource dataSource) {
                    return false;
                }

                @Override
                public boolean isResourceCacheable(boolean isFromAlternateCacheKey, DataSource dataSource, EncodeStrategy encodeStrategy) {
                    return false;
                }

                @Override
                public boolean decodeCachedResource() {
                    return false;
                }

                @Override
                public boolean decodeCachedData() {
                    return false;
                }
            };

            int erroImg = -1;
            try{
                erroImg = Integer.parseInt(imageUrl);
            }catch(NumberFormatException e){
                erroImg = R.drawable.no_image;
            }

            Glide.with(activityContext)
                    .load(imageUrl)
                    .error(erroImg)
                    .diskCacheStrategy(diskCacheStrategy)
                    .into(themeImageLeft);
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
