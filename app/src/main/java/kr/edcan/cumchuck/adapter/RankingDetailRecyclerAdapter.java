package kr.edcan.cumchuck.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.data.CommonRecycleData;
import kr.edcan.cumchuck.data.RankingDetailViewData;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.RoundImageView;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class RankingDetailRecyclerAdapter extends RecyclerView.Adapter<RankingDetailRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<RankingDetailViewData> arrayList;
    RankingDetailViewData data;

    public RankingDetailRecyclerAdapter(Context context, ArrayList<RankingDetailViewData> items) {
        this.context = context;
        this.arrayList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_detail_cardview, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        data = arrayList.get(position);
        if (data.isHeader()) {
            // If isHeader, make Header Layout VISIBLE
            holder.headerLayout.setVisibility(View.VISIBLE);
            holder.headerTitle.setText(data.getHeaderTitle());
            holder.headerRating.setText(new DecimalFormat("#0.0").format(data.getHeaderRating()));
            holder.headerRanking.setText(data.getHeaderRanking() + "위");
            holder.headerAddress.setText(data.getHeaderAddress());
            holder.headerContent.setText(data.getHeaderDescription());
            holder.favorite.setImageResource(CumChuckHelper.star[(data.isFavorite()) ? 0 : 1]);
        } else {
            holder.cardviewLayout.setVisibility(View.VISIBLE);
            holder.cardviewAuthor.setText(data.getCardviewAuthor());
            holder.cardviewContent.setText(data.getCardviewContent());
            holder.cardviewDate.setText(new SimpleDateFormat("yyyy년 MM월 dd일 작성", Locale.KOREA).format(data.getCardviewDate()));
            holder.cardviewRating.setText(new DecimalFormat("#0.0").format(data.getCardviewRating()));
            holder.cardviewTitle.setText(data.getCardviewTitle());
//            holder.cardviewProfileImage.setImageResource();
            holder.cardviewbg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "asdf", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout headerLayout, cardviewbg;
        CardView cardviewLayout;
        TextView headerRating, headerRanking, headerTitle, headerAddress, headerContent;
        ImageView favorite;

        TextView cardviewAuthor, cardviewDate, cardviewRating, cardviewTitle, cardviewContent;
        RoundImageView cardviewProfileImage;

        public ViewHolder(View itemView) {
            super(itemView);
            headerLayout = (LinearLayout) itemView.findViewById(R.id.ranking_detail_header_view);
            cardviewLayout = (CardView) itemView.findViewById(R.id.ranking_detail_cardview_view);
            headerRanking = (TextView) itemView.findViewById(R.id.detail_header_ranking);
            headerRating = (TextView) itemView.findViewById(R.id.detail_header_rating);
            headerTitle = (TextView) itemView.findViewById(R.id.detail_header_title);
            headerAddress = (TextView) itemView.findViewById(R.id.detail_header_address);
            headerContent = (TextView) itemView.findViewById(R.id.detail_header_content);
            favorite = (ImageView) itemView.findViewById(R.id.detail_header_star);

            cardviewAuthor = (TextView) itemView.findViewById(R.id.ranking_detail_cardview_author);
            cardviewDate = (TextView) itemView.findViewById(R.id.ranking_detail_cardview_date);
            cardviewRating = (TextView) itemView.findViewById(R.id.ranking_detail_cardview_rating);
            cardviewTitle = (TextView) itemView.findViewById(R.id.ranking_detail_cardview_title);
            cardviewContent = (TextView) itemView.findViewById(R.id.ranking_detail_cardview_content);
            cardviewProfileImage = (RoundImageView) itemView.findViewById(R.id.ranking_detail_cardview_image);
            cardviewbg = (LinearLayout) itemView.findViewById(R.id.ranking_detail_cardview_bg);

        }
    }
}