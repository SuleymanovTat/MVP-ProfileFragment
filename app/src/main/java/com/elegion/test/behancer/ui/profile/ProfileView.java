package com.elegion.test.behancer.ui.profile;

import android.support.annotation.NonNull;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.user.User;

public interface ProfileView extends BaseView {

    void showProfile(@NonNull User user);
}
