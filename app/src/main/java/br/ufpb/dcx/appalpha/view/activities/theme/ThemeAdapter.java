package br.ufpb.dcx.appalpha.view.activities.theme;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
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

import br.ufpb.dcx.appalpha.control.util.ImageLoadUtil;
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
                holder.themeName.setText(themes.get(position).getName());
                ImageLoadUtil.getInstance().loadImage(themes.get(position).getImageUrl(), holder.themeImage, activityContext);

                holder.themeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setClickable(false);
                        ThemeActivity.OnClickListener(new ThemeActivity.OnClickListener() {
                            @Override
                            public Theme onItemClicked() {
                                return themes.get(position);
                            }
                        });
                    }
                });
        }

        class ViewHolder extends RecyclerView.ViewHolder {
                ImageView themeImage;
                TextView themeName;

                private ViewHolder(View itemView) {
                        super(itemView);
                        themeImage = itemView.findViewById(R.id.img_left);
                        themeName = itemView.findViewById(R.id.tv_theme_name_left);
                }

        }

}
