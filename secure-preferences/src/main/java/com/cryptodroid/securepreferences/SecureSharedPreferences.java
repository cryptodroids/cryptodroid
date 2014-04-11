package com.cryptodroid.securepreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.cryptodroid.securepreferences.utils.SecuredPreferenceHelper;
import com.cryptodroids.encryption.EncryptionHelper;

import java.util.Map;
import java.util.Set;

/**
 * Decorates SharedPreferences with EncryptionHelper of your choice.
 *
 * @author Chris Jenkins
 */
public class SecureSharedPreferences implements SharedPreferences {

    /**
     * Gets a SecureDefaultSharedPreferences using the EncyptionHelper provided.
     * <p/>
     * Remember if you initialize this SharedPreferences with a different helper your data will be unreadable.
     *
     * @param context application context of some kind
     * @param helper  the {@link com.cryptodroids.encryption.EncryptionHelper} to use to encrypt/decrypt data
     */
    public static SharedPreferences getDefaultSharedPreferences(Context context, EncryptionHelper helper) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new SecureSharedPreferences(defaultSharedPreferences, helper);
    }

    /**
     * Gets a SharedPreferenceInstance by the name and mode passed in.
     * Similar to {@link android.content.Context#getSharedPreferences(String, int)}
     * <p/>
     * Remember if you initialize this SharedPreferences with a different helper your data will be unreadable.
     *
     * @param name   Name of the SharedPreference file
     * @param mode   SharedPrefs file mode
     * @param helper the {@link com.cryptodroids.encryption.EncryptionHelper} to use to encrypt/decrypt data
     * @see Context#MODE_PRIVATE
     * @see Context#MODE_WORLD_READABLE
     * @see Context#MODE_WORLD_WRITEABLE
     * @see Context#MODE_MULTI_PROCESS
     */
    public static SharedPreferences getSharedPreferences(Context context, String name, int mode, EncryptionHelper helper) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        return new SecureSharedPreferences(sharedPreferences, helper);
    }

    private final SharedPreferences sharedPreferences;
    private final SecuredPreferenceHelper helper;

    /**
     * Initializes with a single {@link SharedPreferences}
     * and the {@link com.cryptodroids.encryption.EncryptionHelper} to use.
     *
     * @param preferences      The {@link android.content.SharedPreferences} to use.
     * @param encryptionHelper The {@link com.cryptodroids.encryption.EncryptionHelper} to use.
     */
    private SecureSharedPreferences(SharedPreferences preferences, EncryptionHelper encryptionHelper) {
        this.sharedPreferences = preferences;
        this.helper = new SecuredPreferenceHelper(encryptionHelper);
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public SecuredEditor edit() {
        return new SecuredEditor(helper, sharedPreferences.edit());
    }

    @Override
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return helper.decrypt(sharedPreferences, key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return helper.decrypt(sharedPreferences, key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return helper.decrypt(sharedPreferences, key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return helper.decrypt(sharedPreferences, key, defValue);
    }

    @Override
    public String getString(String key, String defValue) {
        return helper.decrypt(sharedPreferences, key, defValue);
    }

    @TargetApi(value = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return helper.decrypt(sharedPreferences, key, defValues);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    protected SharedPreferences getWrappedSharedPreference() {
        return sharedPreferences;
    }

}
