package com.elegion.test.behancer.ui.user_projects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.common.SingleFragmentActivity;

/**
 * Created by Suleymanovtat.
 */

public class UserProjectsActivity extends SingleFragmentActivity {

    public static final String USER = "USER";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return UserProjectsFragment.newInstance(getIntent().getBundleExtra(USER));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}
