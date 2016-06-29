package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.CommonRecyclerAdapter;
import kr.edcan.cumchuck.model.CommonRecycleData;

public class RankingShowActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_show);
        setAppbarLayout();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setAppbarLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("랭킹");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ranking_search:
                // Launch Search Activity
                Toast.makeText(RankingShowActivity.this, "", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class RankingShowFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "pageNumber";

        public RankingShowFragment() {
        }

        public static RankingShowFragment newInstance(int sectionNumber) {
            RankingShowFragment fragment = new RankingShowFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = null;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 0:
                    rootView = inflater.inflate(R.layout.ranking_show_star, container, false);
                    setStarPage(rootView);
                    break;
                case 1:
                    rootView = inflater.inflate(R.layout.ranking_show_visit, container, false);
                    setVisitPage(rootView);
                    break;
            }
            return rootView;
        }

        public void setStarPage(View mainView) {
            RecyclerView starView = (RecyclerView) mainView.findViewById(R.id.ranking_show_star_recyclerview);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            starView.setHasFixedSize(true);
            starView.setLayoutManager(manager);
            ArrayList<CommonRecycleData> arrayList = new ArrayList<>();
//            public CommonRecycleData(String title, String address, String content, double rating, int rankingCount, int visitorsCount, boolean isFavorite) {
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 1, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 2, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, -1, false));
            CommonRecyclerAdapter adapter = new CommonRecyclerAdapter(getContext(), 0, arrayList, v -> {

            });
            starView.setAdapter(adapter);
        }

        public void setVisitPage(View mainView) {
            RecyclerView visitView = (RecyclerView) mainView.findViewById(R.id.ranking_show_visit_recyclerview);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            visitView.setHasFixedSize(true);
            visitView.setLayoutManager(manager);
            ArrayList<CommonRecycleData> arrayList = new ArrayList<>();
//            public CommonRecycleData(String title, String address, String content, double rating, int rankingCount, int visitorsCount, boolean isFavorite) {
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 1, 20405, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 2, 124, false));
            arrayList.add(new CommonRecycleData("title", "address", "content", 1.22, 3, 252, false));
            CommonRecyclerAdapter adapter = new CommonRecyclerAdapter(getContext(), 1, arrayList, v -> {

            });
            visitView.setAdapter(adapter);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a RankingShowFragment (defined as a static inner class below).
            return RankingShowFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "별점이 많은 음식점";
                case 1:
                    return "방문수가 많은 음식점";
            }
            return null;
        }
    }

}
