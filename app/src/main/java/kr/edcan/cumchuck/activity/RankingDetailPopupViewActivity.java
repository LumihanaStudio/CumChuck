package kr.edcan.cumchuck.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import kr.edcan.cumchuck.R;

public class RankingDetailPopupViewActivity extends AppCompatActivity {

    LinearLayout LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackgroundWindow();
        setContentView(R.layout.activity_ranking_detail_popup_view);
        setDefault();
    }

    private void setDefault() {
        LinearLayout = (LinearLayout) findViewById(R.id.spaceBackground);
        LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.no_change, R.anim.slide_down);
            }
        });
    }

    private void setBackgroundWindow() {
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.75f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}

