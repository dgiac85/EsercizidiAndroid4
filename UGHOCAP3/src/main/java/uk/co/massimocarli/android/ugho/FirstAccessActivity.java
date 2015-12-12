package uk.co.massimocarli.android.ugho;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import uk.co.massimocarli.android.ugho.model.UserModel;

import java.util.HashMap;
import java.util.List;

/**
 * This is the Activity we use for the main menu at the first execution of the app
 */
public class FirstAccessActivity extends Activity {

    /**
     * The Tag for the Log
     */
    private static final String TAG_LOG = FirstAccessActivity.class.getName();

    /**
     * Id for the login request
     */
    private static final int LOGIN_REQUEST_ID = 1;

    /**
     * Id for the registration request
     */
    private static final int REGISTRATION_REQUEST_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // We get the reference to the Buttons and manage events
        final Button anonymousButton = (Button) findViewById(R.id.anonymous_button);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We enter as anonymous
                enterAsAnonymous();
            }
        });
        final Button registrationButton = (Button) findViewById(R.id.register_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We manage a registration
                doRegistration();
            }
        });
        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We manage a login
                doLogin();
            }
        });
    }

    /**
     * In this case we simply enter into our application with an implicit Intent
     * as done before
     */
    private void enterAsAnonymous() {
        Log.d(TAG_LOG, "Anonymous access");
        // We access the application in anonymous way
        final Intent anonymousIntent = new Intent(this, MenuActivity.class);
        final UserModel userModel = UserModel.create(System.currentTimeMillis());
        anonymousIntent.putExtra(MenuActivity.USER_EXTRA, userModel);
        startActivity(anonymousIntent);
    }

    /**
     * Invoked to start the registration process
     */
    private void doRegistration() {
        // We create the Intent for the Registration
        final Intent registrationIntent = new Intent(RegisterActivity.REGISTRATION_ACTION);
        startActivityForResult(registrationIntent, REGISTRATION_REQUEST_ID);
    }

    /**
     * This method is invoked when we press the Login button.
     */
    private void doLogin() {
        // We create the Intent for the Login
        final Intent loginIntent = new Intent(LoginActivity.LOGIN_ACTION);
        startActivityForResult(loginIntent, LOGIN_REQUEST_ID);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_REQUEST_ID) {
            // We check the result
            switch (resultCode) {
                case RESULT_OK:
                    // The login is successful. We close this activity and go to the
                    // Main after getting the result
                    final UserModel userModel = (UserModel) data.getParcelableExtra(LoginActivity.USER_DATA_EXTRA);
                    // we go to the main
                    final Intent mainIntent = new Intent(this, MenuActivity.class);
                    mainIntent.putExtra(MenuActivity.USER_EXTRA, userModel);
                    startActivity(mainIntent);
                    finish();
                    break;
                case RESULT_CANCELED:
                    // Login cancelled so we do nothing
                    break;
            }
        } else if (requestCode == REGISTRATION_REQUEST_ID) {
            // We check the result
            switch (resultCode) {
                case RESULT_OK:
                    // The registration is successful. We close this activity and go to the
                    // Main after getting the result
                    final UserModel userModel = (UserModel) data.getParcelableExtra(RegisterActivity.USER_DATA_EXTRA);
                    // we go to the main
                    final Intent detailIntent = new Intent(ShowUserDataActivity.SHOW_USER_ACTION);
                    detailIntent.putExtra(ShowUserDataActivity.USER_EXTRA, userModel);
                    startActivity(detailIntent);
                    break;
                case RESULT_CANCELED:
                    // Login cancelled so we do nothing
                    break;
            }
        }
    }

}
