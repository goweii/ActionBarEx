package per.goweii.android.actionbarex;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import per.goweii.actionbarex.SimpleActionBar;
import per.goweii.actionbarex.listener.OnLeftImageClickListener;
import per.goweii.actionbarex.listener.OnLeftTextClickListener;
import per.goweii.actionbarex.listener.OnRightImageClickListener;
import per.goweii.actionbarex.listener.OnRightTextClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        SimpleActionBar simple_action_bar = findViewById(R.id.simple_action_bar);
        simple_action_bar.setOnLeftImageClickListener(new OnLeftImageClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(context, "onLeftImageClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar.setOnLeftTextClickListener(new OnLeftTextClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(context, "onLeftTextClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar.setOnRightTextClickListener(new OnRightTextClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(context, "onRightTextClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar.setOnRightImageClickListener(new OnRightImageClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(context, "onRightImageClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
