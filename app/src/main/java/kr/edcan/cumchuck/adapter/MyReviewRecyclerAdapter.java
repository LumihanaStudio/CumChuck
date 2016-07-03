package kr.edcan.cumchuck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.MyReviewData;
import kr.edcan.cumchuck.model.RaidFromFriendData;
import kr.edcan.cumchuck.utils.RoundImageView;

/**
 * Created by MalangDesktop on 2016-06-04.
 */


public class MyReviewRecyclerAdapter extends RecyclerView.Adapter<MyReviewRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<MyReviewData> arrayList;
    MyReviewData data;

    public MyReviewRecyclerAdapter(Context context, ArrayList<MyReviewData> items) {
        this.context = context;
        this.arrayList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myreview_cardview_content, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        data = arrayList.get(position);
        holder.title.setText(data.getRes().getName());
        holder.address.setText(data.getRes().getAddress());
        holder.review.setText(data.getReviewcontent());
        holder.foreG.setOnClickListener(view -> Toast.makeText(context, "WOW", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, address, review;
        RelativeLayout foreG;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.myreview_cardview_title);
            address = (TextView) itemView.findViewById(R.id.myreview_cardview_address);
            review = (TextView) itemView.findViewById(R.id.myreview_cardview_content);
            foreG = (RelativeLayout) itemView.findViewById(R.id.myreview_cardview_foreground);
        }
    }

}