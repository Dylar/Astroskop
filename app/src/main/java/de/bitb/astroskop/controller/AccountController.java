package de.bitb.astroskop.controller;


import android.text.TextUtils;

import javax.inject.Inject;

import de.bitb.astroskop.utils.SharedPreferencesUtils;
import de.bitb.astroskop.api.LoginCallback;


public class AccountController {

    private static final String KEY_SAVED_CREDENTIALS = "saved_credentials";

    private final SharedPreferencesUtils sharedPrefUtils;
    private boolean loggedIn;

    @Inject
    public AccountController(SharedPreferencesUtils sharedPreferencesUtils) {
        this.sharedPrefUtils = sharedPreferencesUtils;
    }

    public boolean isLoggedIn() {
        return loggedIn || !TextUtils.isEmpty(getSavedCredentials());
    }

    public void saveCredentials(String userName) {
        sharedPrefUtils.putString(KEY_SAVED_CREDENTIALS, userName);
    }

    public String getSavedCredentials() {
        return sharedPrefUtils.getString(KEY_SAVED_CREDENTIALS, "");
    }

    private void removeCredentials() {
        sharedPrefUtils.remove(KEY_SAVED_CREDENTIALS);
    }

    public void login(String userName, String password, boolean member, LoginCallback loginCallback) {

        // TODO wait for API
//        Call<Account> call = apiInterface.login(new CredentialsDTO(userName, password));
//        ApiCallback<Account> callback = new ApiCallback<Account>() {
//
//            @Override
//            public void onSuccess(Call call, Account response) {
        loggedIn = true;
        if (member) {
            saveCredentials(userName);
        }
//                // TODO save account what ever
        loginCallback.onSuccess();
//            }
//
//            @Override
//            public void onClientError(Call<Account> call, Response<Account> response) {
//                super.onClientError(call, response);
//                loginCallback.onFailure();
//
//            }
//        };
//        call.enqueue(callback);
    }

    public void logout() {
        removeCredentials();
        loggedIn = false;
    }

}
