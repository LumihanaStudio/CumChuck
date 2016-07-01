package kr.edcan.cumchuck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.RaidFromFriendData;
import kr.edcan.cumchuck.utils.RoundImageView;

/**
 * Created by MalangDesktop on 2016-06-04.
 */


public class RaidFromFriendRecyclerAdapter extends RecyclerView.Adapter<RaidFromFriendRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<RaidFromFriendData> arrayList;
    RaidFromFriendData data;
    public RaidFromFriendRecyclerAdapter(Context context, ArrayList<RaidFromFriendData> items) {
        this.context = context;
        this.arrayList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.raidfromfriend_cardview_content, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        data = arrayList.get(position);
        holder.title.setText(data.getTitle());
        holder.authoranddate.setText(data.getAuthor_date());
        holder.resTitle.setText(data.getResTitle());
        holder.resAddress.setText(data.getResAddress());
//        holder.profileImage.setImageDrawable(new);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, authoranddate, resTitle, resAddress;
        RoundImageView profileImage;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.raidfromfriend_cardview_title);
            authoranddate = (TextView) itemView.findViewById(R.id.raidfromfriend_cardview_date_and_author);
            resTitle = (TextView) itemView.findViewById(R.id.raidfromfriend_cardview_resTitle);
            resAddress = (TextView) itemView.findViewById(R.id.raidfromfriend_cardview_resAddress);
            profileImage = (RoundImageView) itemView.findViewById(R.id.raidfromfriend_cardview_profileImage);
        }
    }

}