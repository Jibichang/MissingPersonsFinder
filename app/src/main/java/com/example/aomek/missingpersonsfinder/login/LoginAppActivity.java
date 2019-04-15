package com.example.aomek.missingpersonsfinder.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.model.Guest;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.profile.SettingActivity;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_GUEST;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_EMAIL;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PLACE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PHONE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;


import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginAppActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mNameView;
    private EditText mPlaceView;
    private EditText mPhoneView;


    private View mProgressView;
    private View mLoginFormView;

    private View placeInput;
    private View phoneInput;
    private View nameInput;
    private View textRegist;
    private View buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        // Set Database
        mHelper = new DatabaseHelper(LoginAppActivity.this);
        mDb = mHelper.getWritableDatabase();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNameView = findViewById(R.id.user);
        mPlaceView = findViewById(R.id.place);
        mPhoneView = findViewById(R.id.phone);

        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLoginView();
                attemptLogin();
            }
        });

        placeInput = findViewById(R.id.layout_regist_place);
        phoneInput = findViewById(R.id.layout_regist_phone);
        nameInput = findViewById(R.id.layout_regist_name);

        textRegist = findViewById(R.id.textView_regist);
        buttonLogin = findViewById(R.id.email_sign_in_button);


        Button registerButtom = findViewById(R.id.email_sign_up_button);
        registerButtom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                placeInput.setVisibility(View.VISIBLE);
                phoneInput.setVisibility(View.VISIBLE);
                nameInput.setVisibility(View.VISIBLE);

                textRegist.setVisibility(View.GONE);
                buttonLogin.setVisibility(View.GONE);

                attemptRegister();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private void setLoginView(){
        placeInput.setVisibility(View.GONE);
        phoneInput.setVisibility(View.GONE);
        nameInput.setVisibility(View.GONE);

        textRegist.setVisibility(View.VISIBLE);
        buttonLogin.setVisibility(View.VISIBLE);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (password != "" && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
//            Toast.makeText(getApplicationContext(), " ok" + email + " : " +password, Toast.LENGTH_LONG).show();
              loginGuest(email, password);
        }
    }

    private void attemptRegister() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        String name = mNameView.getText().toString();
        String place = mPlaceView.getText().toString();
        String phone = mPhoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (password != "" && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Set name = email when name empty
        if (name == "" || name.isEmpty()){
            name = email;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            registGuest(name, email, password, place, phone);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginAppActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private void loginGuest(String email, String password) {
        showProgress(true);
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);

        email = email.trim();
        password = password.trim();

        Guest obGuest = new Guest(email, password);
        Call<Guest> call = retrofit.Login(obGuest);
        call.enqueue(new Callback<Guest>() {
            @Override
            public void onResponse(Call<Guest> call, Response<Guest> response) {
                if (response.body() != null) {
                    Guest guest = response.body();
                    String gid = guest.getGuestId();
                    String name = guest.getName();
                    String email = guest.getEmail();
                    String place = guest.getPlace();
                    String phone = guest.getPhone();

                    Toast.makeText(getApplicationContext(), "เข้าสู่ระบบแล้ว", Toast.LENGTH_LONG).show();
                    doInsertItem(gid, name, email, place, phone);
                }else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ถูกต้อง", Toast.LENGTH_LONG).show();
                }

                showProgress(false);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "เเข้าสู่ระบบล้มเหลว" + t, Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        });
    }

    private void registGuest(String name, String email, String password, String place, String phone) {
        showProgress(true);
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);

        name = name.trim();
        email = email.trim();
        password = password.trim();
        place = place.trim();
        phone = phone.trim();

        Guest obGuestRegister = new Guest(name, email, password, place, phone);
        Call<Guest> callRegister = retrofit.Register(obGuestRegister);
        callRegister.enqueue(new Callback<Guest>() {
            @Override
            public void onResponse(Call<Guest> call, Response<Guest> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "สมัครสมาชิกแล้ว", Toast.LENGTH_LONG).show();
                    setLoginView();
                    mPasswordView.setText("");
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "สมัครสมาชิกล้มเหลว" + t, Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        });

//        showProgress(true);
//
//        Guest obGuestLogin = new Guest(email, password);
//        Call<Guest> callLogin = retrofit.Register(obGuestLogin);
//        callLogin.enqueue(new Callback<Guest>() {
//            @Override
//            public void onResponse(Call<Guest> call, Response<Guest> response) {
//                if (response.body() != null) {
//                    Guest guest = response.body();
//                    String gid = guest.getGuestId();
//                    String name = guest.getName();
//                    String email = guest.getEmail();
//                    String place = guest.getPlace();
//                    String phone = guest.getPhone();
//
//                    Toast.makeText(getApplicationContext(), "เข้าสู่ระบบแล้ว", Toast.LENGTH_LONG).show();
////                    setLoginView();
//                    doInsertItem(gid, name, email, place, phone);
//                }
//                showProgress(false);
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "เข้าสู่ระบบล้มเหลว" + t, Toast.LENGTH_LONG).show();
//                showProgress(false);
//            }
//        });
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        private final String mLoginURL = "UserLoginTask";

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.

                Thread.sleep(2000);

            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Button loginButton = findViewById(R.id.email_sign_in_button);
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LoginAppActivity.this, MainActivity.class);
//                        startActivity(i);
                    }
                });
//                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }


    private void doInsertItem(String gid, String name, String email, String place, String phone) {
        mDb.delete(TABLE_NAME, null, null);

        ContentValues cv = new ContentValues();
        cv.put(COL_GUEST, gid);
        cv.put(COL_NAME, name);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PLACE, place);
        cv.put(COL_PHONE, phone);
        mDb.insert(TABLE_NAME, null, cv);

        Lost.onStatusLogin = true;
//        finish();
        startActivity( new Intent(LoginAppActivity.this, MainActivity.class));
//        Toast.makeText(getApplicationContext(), "ok " + cv.toString(), Toast.LENGTH_LONG).show();
//        finish();
    }


}

