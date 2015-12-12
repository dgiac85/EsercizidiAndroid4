package uk.co.massimocarli.android.ugho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.model.UserModel;
import uk.co.massimocarli.android.ugho.service.LoginService;

/**
 * This is the Activity we use to manage the Login
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class LoginActivity extends Activity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = LoginActivity.class.getName();

    /**
     * The action we use to manage the Login
     */
    public static final String LOGIN_ACTION = Const.PKG + ".action.LOGIN_ACTION";

    /**
     * The key for the extra that we use to get the UserData after login
     */
    public static final String USER_DATA_EXTRA = Const.PKG + ".extra.USER_DATA_EXTRA";

    /**
     * The reference to the EditText for the username
     */
    private EditText mUsernameEditText;

    /**
     * The reference to the EditText for the password
     */
    private EditText mPasswordEditText;

    /**
     * The reference to the TextView for the error messages
     */
    private TextView mErrorTextView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // We get the reference of the EditText
        mUsernameEditText = (EditText) findViewById(R.id.username_edittext);
        mPasswordEditText = (EditText) findViewById(R.id.password_edittext);
        // The reference to the TextView for error
        mErrorTextView = (TextView) findViewById(R.id.error_message_label);
    }

    /**
     * This is invoked when we press the login button
     *
     * @param loginButton The Button pressed
     */
    public void doLogin(View loginButton) {
        Log.d(TAG_LOG, "doLogin!");
        // We hide the error message
        this.mErrorTextView.setVisibility(View.INVISIBLE);
        // We check if the username and password are present
        final Editable usernameEdit = mUsernameEditText.getText();
        if (TextUtils.isEmpty(usernameEdit)) {
            final String usernameMandatory = getResources().getString(R.string.mandatory_field_error, "username");
            Log.w(TAG_LOG, usernameMandatory);
            this.mErrorTextView.setText(usernameMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }
        final Editable passwordEdit = mPasswordEditText.getText();
        if (TextUtils.isEmpty(passwordEdit)) {
            final String passwordMandatory = getResources().getString(R.string.mandatory_field_error, "password");
            Log.w(TAG_LOG, passwordMandatory);
            this.mErrorTextView.setText(passwordMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }
        // We get the credentials as Strings
        final String username = usernameEdit.toString();
        final String password = passwordEdit.toString();
        final UserModel userModel = LoginService.get().login(username, password);
        if (userModel != null) {
            Log.d(TAG_LOG, "User authenticated!");
            // In this case the user is authenticated so we can return the UserData
            Intent resultIntent = new Intent();
            // If this userModel is not Serializable or Parcelable we'll get
            // a compilation error.
            resultIntent.putExtra(USER_DATA_EXTRA, userModel);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            // in this case the user is not authenticated so we show an error message
            this.mErrorTextView.setText(R.string.wrong_credential_error);
            this.mErrorTextView.setVisibility(View.VISIBLE);
        }
    }

}