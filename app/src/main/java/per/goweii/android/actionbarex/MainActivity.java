package per.goweii.android.actionbarex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import per.goweii.actionbarex.ActionBarEx;
import per.goweii.actionbarex.common.OnActionBarChildClickListener;
import per.goweii.actionbarex.common.ActionBarCommon;
import per.goweii.actionbarex.common.ActionBarSearch;

public class MainActivity extends AppCompatActivity {

    private ActionBarCommon simple_action_bar_1;
    private ActionBarCommon simple_action_bar_2;
    private ActionBarCommon simple_action_bar_3;
    private ActionBarSearch search_action_bar_1;
    private ActionBarSearch search_action_bar_2;
    private ActionBarEx action_bar_ex_1;
    private ActionBarEx action_bar_ex_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        simple_action_bar_1 = findViewById(R.id.simple_action_bar_1);
        simple_action_bar_2 = findViewById(R.id.simple_action_bar_2);
        simple_action_bar_3 = findViewById(R.id.simple_action_bar_3);
        search_action_bar_1 = findViewById(R.id.search_action_bar_1);
        search_action_bar_2 = findViewById(R.id.search_action_bar_2);
        action_bar_ex_1 = findViewById(R.id.action_bar_ex_1);
        action_bar_ex_2 = findViewById(R.id.action_bar_ex_2);

        simple_action_bar_1.setOnLeftIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onLeftImageClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_1.setOnLeftTextClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onLeftTextClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_1.setOnRightTextClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onRightTextClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_1.setOnRightIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onRightImageClick", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.tv_test_in_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestInFragmentActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_show_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simple_action_bar_1.getForegroundLayer().setVisibility(View.VISIBLE);
                simple_action_bar_2.getForegroundLayer().setVisibility(View.VISIBLE);
                simple_action_bar_3.getForegroundLayer().setVisibility(View.VISIBLE);
                search_action_bar_1.getForegroundLayer().setVisibility(View.VISIBLE);
                search_action_bar_2.getForegroundLayer().setVisibility(View.VISIBLE);
                action_bar_ex_1.getForegroundLayer().setVisibility(View.VISIBLE);
                action_bar_ex_2.getForegroundLayer().setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simple_action_bar_1.getForegroundLayer().setVisibility(View.GONE);
                        simple_action_bar_2.getForegroundLayer().setVisibility(View.GONE);
                        simple_action_bar_3.getForegroundLayer().setVisibility(View.GONE);
                        search_action_bar_1.getForegroundLayer().setVisibility(View.GONE);
                        search_action_bar_2.getForegroundLayer().setVisibility(View.GONE);
                        action_bar_ex_1.getForegroundLayer().setVisibility(View.GONE);
                        action_bar_ex_2.getForegroundLayer().setVisibility(View.GONE);
                    }
                }, 2000);
            }
        });
    }
}
