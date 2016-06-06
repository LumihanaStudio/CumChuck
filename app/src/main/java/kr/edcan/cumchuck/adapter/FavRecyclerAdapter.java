package kr.edcan.cumchuck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.data.FavoriteData;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<FavoriteData> arrayList;
    FavoriteData data;
    int star[] = {R.drawable.btn_fav_favstar, R.drawable.btn_fav_favstar_off};

    public FavRecyclerAdapter(Context context, ArrayList<FavoriteData> items) {
        this.context = context;
        this.arrayList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_restaurant_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        data = arrayList.get(position);
        holder.title.setText(data.getTitle());
        holder.content.setText(data.getContent());
        holder.address.setText(data.getAddress());
        holder.rating.setText(new DecimalFormat("#0.0").format(data.getStar()));
//        holder.background
        holder.starimg.setImageResource(star[(data.isFavorite()) ? 0 : 1]);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rating, title, address, content;
        ImageView starimg, background;

        public ViewHolder(View itemView) {
            super(itemView);
            rating = (TextView) itemView.findViewById(R.id.common_rating);
            title = (TextView) itemView.findViewById(R.id.common_title);
            address = (TextView) itemView.findViewById(R.id.common_address);
            content = (TextView) itemView.findViewById(R.id.common_content);
            starimg = (ImageView) itemView.findViewById(R.id.common_star);
            background = (ImageView) itemView.findViewById(R.id.common_background);
            
            starimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setFavorite(!data.isFavorite());
                    starimg.setImageResource(star[(data.isFavorite()) ? 0 : 1]);
                }
            });
        }
    }
}