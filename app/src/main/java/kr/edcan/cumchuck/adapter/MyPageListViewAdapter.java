package kr.edcan.cumchuck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.data.NormalPreferenceListData;

/**
 * Created by MalangDesktop on 2016-05-08.
 */
public class MyPageListViewAdapter extends ArrayAdapter<NormalPreferenceListData> {
    private LayoutInflater inflater;

    public MyPageListViewAdapter(Context c, ArrayList<NormalPreferenceListData> normalPreferenceListDatas){
        super(c,0,normalPreferenceListDatas);
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.mypage_listview_content, null);

        NormalPreferenceListData data = this.getItem(position);
        if(data != null){
            ImageView imageView = (ImageView) view.findViewById(R.id.mypage_listview_imageview);
            TextView title = (TextView) view.findViewById(R.id.mypage_listview_title);
            TextView content = (TextView) view.findViewById(R.id.mypage_listview_content);
            imageView.setImageResource(data.getImage());
            title.setText(data.getTitle());
            content.setText(data.getContent());
        }
        return view;
    }
}
