package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kr.edcan.cumchuck.R;

public class NetworkErrorActivity extends AppCompatActivity {

    public static final int WIFI_OR_DATA_NOT_CONNECTED = 0;
    public static final int SERVER_ERROR = 1;
    int errorType = -1;
    Intent intent;

    TextView currentErrorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_error);
        setDefault();
    }

    private void setDefault() {

        intent = getIntent();
        switch (intent.getIntExtra("ERROR_TYPE", -1)) {
            case -1:
                finish();
                break;
            case WIFI_OR_DATA_NOT_CONNECTED:
                break;
            case SERVER_ERROR:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
