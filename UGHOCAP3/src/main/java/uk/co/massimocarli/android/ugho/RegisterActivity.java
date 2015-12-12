package uk.co.massimocarli.android.ugho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.model.UserModel;
import uk.co.massimocarli.android.ugho.service.LoginService;
import uk.co.massimocarli.android.ugho.service.RegistrationService;

import java.util.Calendar;

/**
 * This is the Activity we use to manage the Login
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class RegisterActivity extends Activity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = RegisterActivity.class.getName();

    /**
     * The action we use to manage the Registration
     */
    public static final String REGISTRATION_ACTION = Const.PKG + ".action.REGISTRATION_ACTION";

    /**
     * The key for the extra that we use to get the UserData after registration
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
     * The reference to the EditText for the email
     */
    private EditText mEmailEditText;

    /**
     * The reference to the EditText for the birth date
     */
    private DatePicker mBirthDatePicker;

    /**
     * The reference to the EditText for the location
     */
    private EditText mLocationEditText;

    /**
     * The reference to the TextView for the error messages
     */
    private TextView mErrorTextView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // We get the reference of the EditText
        mUsernameEditText = (EditText) findViewById(R.id.username_edittext);
        mPasswordEditText = (EditText) findViewById(R.id.password_edittext);
        mEmailEditText = (EditText) findViewById(R.id.email_edittext);
        mBirthDatePicker = (DatePicker) findViewById(R.id.birth_date_datepicker);
        mLocationEditText = (EditText) findViewById(R.id.location_edittext);
        // The reference to the TextView for error
        mErrorTextView = (TextView) findViewById(R.id.error_message_label);
    }

    /**
     * This is invoked when we press the registration button
     *
     * @param regButton The Button pressed
     */
    public void doRegistration(View regButton) {
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
        final Editable emailEdit = mEmailEditText.getText();
        if (TextUtils.isEmpty(emailEdit)) {
            final String emailMandatory = getResources().getString(R.string.mandatory_field_error, "email");
            Log.w(TAG_LOG, emailMandatory);
            this.mErrorTextView.setText(emailMandatory);
            this.mErrorTextView.setVisibility(View.VISIBLE);
            return;
        }
        // Data related to the birthDate
        int dayOfMonth = mBirthDatePicker.getDayOfMonth();
        int month = mBirthDatePicker.getMonth();
        int year = mBirthDatePicker.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);
        final long birthDate = cal.getTimeInMillis();
        // Data about the Location
        final Editable locationEdit = mLocationEditText.getText();
        String location = null;
        if (!TextUtils.isEmpty(locationEdit)) {
            location = locationEdit.toString();
        }
        // We get the info as Strings
        final String username = usernameEdit.toString();
        final String password = passwordEdit.toString();
        final String email = emailEdit.toString();
        final UserModel userModel = RegistrationService.get().register(username, password, email, birthDate, location);
        if (userModel != null) {
            Log.d(TAG_LOG, "User registered and authenticated!");
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