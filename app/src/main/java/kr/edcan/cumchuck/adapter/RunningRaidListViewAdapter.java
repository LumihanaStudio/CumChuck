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
import kr.edcan.cumchuck.model.User;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.RoundImageView;

import static android.R.attr.data;

/**
 * Created by MalangDesktop on 2016-05-08.
 */
public class RunningRaidListViewAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflater;
    private ArrayList<User> arrayList;
    public RunningRaidListViewAdapter(Context c, ArrayList<User> pairs) {
        super(c, 0, pairs);
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = pairs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.runningraid_listview_content, null);
        User pair = arrayList.get(position);

        RoundImageView imageview = (RoundImageView) view.findViewById(R.id.runningraid_listview_image);
        imageview.setImageResource(CumChuckHelper.returnRandomAyano());
        TextView name = (TextView) view.findViewById(R.id.runningraid_listview_name);
        name.setText(pair.getName());
        return view;
    }
}
