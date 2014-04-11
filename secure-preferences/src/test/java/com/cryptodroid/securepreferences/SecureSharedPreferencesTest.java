package com.cryptodroid.securepreferences;

import android.app.Application;
import android.content.Context;

import com.cryptodroids.encryption.NoEncryptionHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by chris on 11/04/2014.
 * For cryptodroid.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class SecureSharedPreferencesTest {

    private Application application;
    private SecureSharedPreferences secureSharedPreferences;
    private NoEncryptionHelper noEncryptionHelper = new NoEncryptionHelper();

    @Before
    public void setUp() throws Exception {
        application = Robolectric.application;
    }

    @After
    public void tearDown() throws Exception {
        application = null;
    }

    @Test
    public void testGetDefaultSharedPreference() throws Exception {
        final SecureSharedPreferences defaultSecureSharedPreferences = (SecureSharedPreferences) SecureSharedPreferences.getDefaultSharedPreferences(application, noEncryptionHelper);

        // Check instantiation
        assertThat(defaultSecureSharedPreferences).isNotNull();

        // Check populated
        assertThat(defaultSecureSharedPreferences.getWrappedSharedPreference()).isNotNull();
    }

    @Test
    public void testGetSharedPreference() throws Exception {
        final SecureSharedPreferences secureSharedPreferences = (SecureSharedPreferences) SecureSharedPreferences.getSharedPreferences(application, "secure", Context.MODE_PRIVATE, noEncryptionHelper);

        // Check instantiation
        assertThat(secureSharedPreferences).isNotNull();

        // Check populated
        assertThat(secureSharedPreferences.getWrappedSharedPreference()).isNotNull();
    }

    @Test
    public void testContains() throws Exception {

    }

    @Test
    public void testEdit() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetBoolean() throws Exception {

    }

    @Test
    public void testGetFloat() throws Exception {

    }

    @Test
    public void testGetInt() throws Exception {

    }

    @Test
    public void testGetLong() throws Exception {

    }

    @Test
    public void testGetString() throws Exception {

    }

    @Test
    public void testGetStringSet() throws Exception {

    }

    @Test
    public void testRegisterOnSharedPreferenceChangeListener() throws Exception {

    }

    @Test
    public void testUnregisterOnSharedPreferenceChangeListener() throws Exception {

    }
}
