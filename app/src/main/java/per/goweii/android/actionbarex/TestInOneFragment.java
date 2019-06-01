package per.goweii.android.actionbarex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import per.goweii.actionbarex.common.OnActionBarChildClickListener;
import per.goweii.actionbarex.common.ActionBarCommon;

public class TestInOneFragment extends LazyFragment {

    private TestInFragmentActivity mActivity;
    private ActionBarCommon mActionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_in_one;
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
        mActionBar = view.findViewById(R.id.simple_action_bar);
        mActionBar.setOnLeftIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        mActionBar.setOnRightTextClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.switchFragment();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mActionBar.refreshStatusBar();
        }
    }
}
