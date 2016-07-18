package kr.edcan.cumchuck.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.FavRecyclerAdapter;
import kr.edcan.cumchuck.model.FavoriteData;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView favRecycler;
    Call<List<Restaurant>> getFavRestaurant;
    NetworkInterface service;
    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_view);
        setToolbar();
        setData();
    }

    private void setData() {
        manager = new DataManager();
        manager.initializeManager(getApplicationContext());
        service = CumChuckNetworkHelper.getNetworkInstance();
        getFavRestaurant = service.getFavRestaurant(manager.getActiveUser().second.getId());
        getFavRestaurant.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                Log.e("asdf", response.code() + "");
                switch (response.code()) {
                    case 200:
                        setDefault(response.body());
                        break;
                    case 401:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        getSupportActionBar().setTitle("즐겨찾기");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    private void setDefault(List<Restaurant> list) {
        favRecycler = (RecyclerView) findViewById(R.id.favoriteView);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        favRecycler.setHasFixedSize(true);
        favRecycler.setLayoutManager(manager);

        // Throw NetworkManager to Adapter when is able
        ArrayList<FavoriteData> arrayList = new ArrayList<>();
        // if(star>5/0) return;
        for (Restaurant r : list) {
//            arrayList.add(new FavoriteData(true, r.getName(), r.getName(), "", 0.0));
        }
        FavRecyclerAdapter adapter = new FavRecyclerAdapter(getApplicationContext(), arrayList);
        favRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
