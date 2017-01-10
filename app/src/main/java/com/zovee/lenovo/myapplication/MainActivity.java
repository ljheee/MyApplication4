package com.zovee.lenovo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView_name;
    TextView textView_day1;
    TextView textView_day2;
    TextView textView_day3;
    TextView textView_status1;
    TextView textView_status2;
    TextView textView_status3;
    TextView textView_high1;
    TextView textView_high2;
    TextView textView_high3;
    TextView textView_low1;
    TextView textView_low2;
    TextView textView_low3;


    static Handler handler;
    public static final int QUERY_FINISH = 0x000;
    public static final int ERROR = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_name = (TextView) findViewById(R.id.textView_name);
        textView_day1 = (TextView) findViewById(R.id.textView_day1);
        textView_day2 = (TextView) findViewById(R.id.textView_day2);
        textView_day3 = (TextView) findViewById(R.id.textView_day3);
        textView_status1 = (TextView) findViewById(R.id.textView_status1);
        textView_status2 = (TextView) findViewById(R.id.textView_status2);
        textView_status3 = (TextView) findViewById(R.id.textView_status3);
        textView_high1 = (TextView) findViewById(R.id.textView_high1);
        textView_high2 = (TextView) findViewById(R.id.textView_high2);
        textView_high3 = (TextView) findViewById(R.id.textView_high3);
        textView_low1 = (TextView) findViewById(R.id.textView_low1);
        textView_low2 = (TextView) findViewById(R.id.textView_low2);
        textView_low3 = (TextView) findViewById(R.id.textView_low3);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case QUERY_FINISH:

                        Bundle data = msg.getData();
                        String rs = data.getString("value");

                        if (rs != null) {
                            ArrayList<Day> days = JsonUtils.getDays(rs);
                            textView_day1.setText(days.get(0).getDate());
                            textView_day2.setText(days.get(1).getDate());
                            textView_day3.setText(days.get(2).getDate());
                            textView_status1.setText(days.get(0).getText_day());
                            textView_status2.setText(days.get(1).getText_day());
                            textView_status3.setText(days.get(2).getText_day());
                            textView_high1.setText(days.get(0).getHigh());
                            textView_high2.setText(days.get(1).getHigh());
                            textView_high3.setText(days.get(2).getHigh());
                            textView_low1.setText(days.get(0).getLow());
                            textView_low2.setText(days.get(1).getLow());
                            textView_low3.setText(days.get(2).getLow());
                        }
                        Toast.makeText(MainActivity.this, "更新完毕", Toast.LENGTH_SHORT).show();

                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this, "查询出错", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

    }

    //-----------------------选项菜单-------------------------


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_add){
            Intent intent = new Intent(this,ProvinceActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
