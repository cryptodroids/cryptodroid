package com.cryptodroid.securepreferences;

import android.content.SharedPreferences;

import com.cryptodroid.securepreferences.utils.SecuredPreferenceHelper;

import java.util.Set;

/**
 * An {@link android.content.SharedPreferences.Editor} decorator using AES encription.
 *
 * @author NoTiCe
 */
public class SecuredEditor implements SharedPreferences.Editor {
    private SharedPreferences.Editor editor;
    private SecuredPreferenceHelper helper;

    /**
     * Initializes with the {@link com.cryptodroids.encryption.EncryptionHelper} an the original
     * {@link android.content.SharedPreferences.Editor}.
     *
     * @param helper The helper to use.
     * @param edit   The editor to use.
     */
    public SecuredEditor(SecuredPreferenceHelper helper, SharedPreferences.Editor edit) {
        this.helper = helper;
        this.editor = edit;
    }

    @Override
    public SecuredEditor putString(String key, String value) {
        editor.putString(key, helper.encrypt(value));
        return this;
    }

    @Override
    public SecuredEditor putStringSet(String key, Set<String> values) {
        editor.putString(key, helper.encrypt(values));
        return this;
    }

    @Override
    public SecuredEditor putInt(String key, int value) {
        editor.putString(key, helper.encrypt(value));
        return this;
    }

    @Override
    public SecuredEditor putLong(String key, long value) {
        editor.putString(key, helper.encrypt(value));
        return this;
    }

    @Override
    public SecuredEditor putFloat(String key, float value) {
        editor.putString(key, helper.encrypt(value));
        return this;
    }

    @Override
    public SecuredEditor putBoolean(String key, boolean value) {
        editor.putString(key, helper.encrypt(value));
        return this;
    }

    @Override
    public SecuredEditor remove(String key) {
        editor.remove(key);
        return this;
    }

    @Override
    public SecuredEditor clear() {
        editor.clear();
        return this;
    }

    @Override
    public boolean commit() {
        return editor.commit();
    }

    /**
     * We're not responsible for API checking on part of the user. As we only provide the Editor instance to them,
     * their lint tools will pick up apply as invalid
     */
    @Override
    public void apply() {
        editor.apply();
    }

}