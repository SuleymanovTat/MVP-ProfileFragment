package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vladislav Falzan.
 */

public class ProfilePresenter extends BasePresenter {

    private final ProfileView mView;
    private final Storage mStorage;
    private final String mUsername;

    public ProfilePresenter(ProfileView view, Storage storage, String username) {
        mView = view;
        mStorage = storage;
        mUsername = username;
    }

    public void getProfile() {
        mCompositeDisposable.add(ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mStorage::insertUser)
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(mView::hideRefresh)
                .subscribe(
                        response -> {
                            mView.showProfile(response.getUser());
                        },
                        throwable -> {
                            mView.showError();
                        }));
    }
}
