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
import kr.edcan.cumchuck.data.CommonRecycleData;
import kr.edcan.cumchuck.data.FavoriteData;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class CommonRecyclerAdapter extends RecyclerView.Adapter<CommonRecyclerAdapter.ViewHolder> {

    /*
    * 0 : RankingShowActivity - Sort by Rating
    * 1 : RankingShowActivity - Sort by VisitCount
    * 2 : RecommendActivity - Full View
    * 3: NewRaidActivity - SearchView
    * */
    int fragmentPageType;
    Context context;
    ArrayList<CommonRecycleData> arrayList;
    CommonRecycleData data;
    int star[] = {R.drawable.btn_fav_favstar, R.drawable.btn_fav_favstar_off};

    public CommonRecyclerAdapter(Context context, int fragmentPageType, ArrayList<CommonRecycleData> items) {
        this.context = context;
        this.arrayList = items;
        this.fragmentPageType = fragmentPageType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_restaurant_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (fragmentPageType){
            case 0:
                holder.ranking.setVisibility(View.VISIBLE);
                holder.ratingScore.setVisibility(View.VISIBLE);
                holder.star.setVisibility(View.VISIBLE);
                break;
            case 1:
                holder.ranking.setVisibility(View.VISIBLE);
                holder.ratingScore.setVisibility(View.VISIBLE);
                holder.star.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView star;
        TextView title, address, content;
        TextView ratingScore, ranking, visitorsCount;
        public ViewHolder(View itemView) {
            super(itemView);
            title=  (TextView) itemView.findViewById(R.id.common_title);
            address = (TextView) itemView.findViewById(R.id.common_address);
            content = (TextView) itemView.findViewById(R.id.common_content);
            ratingScore = (TextView) itemView.findViewById(R.id.common_rating);
            ranking = (TextView) itemView.findViewById(R.id.common_visitor_rankingst);
            visitorsCount = (TextView) itemView.findViewById(R.id.common_visitor_count);
            star = (ImageView) itemView.findViewById(R.id.common_star);
        }
    }
}