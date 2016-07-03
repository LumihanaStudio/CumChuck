package kr.edcan.cumchuck.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.NormalPreferenceListData;
import kr.edcan.cumchuck.utils.RoundImageView;

import static android.R.attr.data;

/**
 * Created by MalangDesktop on 2016-05-08.
 */
public class RunningRaidListViewAdapter extends ArrayAdapter<Pair<String, String>> {

    private LayoutInflater inflater;
    private ArrayList<Pair<String, String>> arrayList;
    public RunningRaidListViewAdapter(Context c, ArrayList<Pair<String, String>> pairs) {
        super(c, 0, pairs);
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = pairs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.runningraid_listview_content, null);
        Pair<String, String> pair = arrayList.get(position);

        RoundImageView imageview = (RoundImageView) view.findViewById(R.id.runningraid_listview_image);
        TextView name = (TextView) view.findViewById(R.id.runningraid_listview_name);
        name.setText(pair.first);
        return view;
    }
}
