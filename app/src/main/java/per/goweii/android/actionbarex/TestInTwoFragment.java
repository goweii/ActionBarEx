package per.goweii.android.actionbarex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import per.goweii.actionbarex.SimpleActionBar;
import per.goweii.actionbarex.listener.OnLeftImageClickListener;
import per.goweii.actionbarex.listener.OnRightTextClickListener;

public class TestInTwoFragment extends LazyFragment {

    private TestInFragmentActivity mActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_in_two;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TestInFragmentActivity) {
            mActivity = (TestInFragmentActivity) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimpleActionBar simple_action_bar = view.findViewById(R.id.simple_action_bar);
        simple_action_bar.setOnLeftImageClickListener(new OnLeftImageClickListener() {
            @Override
            public void onClick() {
                mActivity.finish();
            }
        });
        simple_action_bar.setOnRightTextClickListener(new OnRightTextClickListener() {
            @Override
            public void onClick() {
                mActivity.switchFragment();
            }
        });
    }

}
