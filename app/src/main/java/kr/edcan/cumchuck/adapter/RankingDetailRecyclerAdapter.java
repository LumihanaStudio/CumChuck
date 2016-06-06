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
import kr.edcan.cumchuck.utils.CumChuckHelper;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class RankingDetailRecyclerAdapter extends RecyclerView.Adapter<RankingDetailRecyclerAdapter.ViewHolder> {

    int fragmentPageType;
    Context context;
    ArrayList<CommonRecycleData> arrayList;
    CommonRecycleData data;

    public RankingDetailRecyclerAdapter(Context context, int fragmentPageType, ArrayList<CommonRecycleData> items) {
        this.context = context;
        this.arrayList = items;
        this.fragmentPageType = fragmentPageType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_detail_cardview, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        data = arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}