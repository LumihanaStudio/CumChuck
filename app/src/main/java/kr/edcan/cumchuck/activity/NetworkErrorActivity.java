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
        currentErrorMsg = (TextView) findViewById(R.id.network_error_mainText);

        intent = getIntent();
        switch (intent.getIntExtra("ERROR_TYPE", -1)) {
            case -1:
                finish();
                break;
            case WIFI_OR_DATA_NOT_CONNECTED:
                currentErrorMsg.setText("데이터 네트워크가 사용이 중지되었습니다.\n연결 후 다시 시도해주세요!");
                break;
            case SERVER_ERROR:
                currentErrorMsg.setText("서버에 연결할 수 없습니다.\n서비스 이용에 불편을드려 죄송합니다.\n");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
