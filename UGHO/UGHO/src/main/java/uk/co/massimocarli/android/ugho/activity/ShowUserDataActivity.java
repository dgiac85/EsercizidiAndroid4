package uk.co.massimocarli.android.ugho.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import uk.co.massimocarli.android.ugho.R;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.model.UserModel;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * This is the Activity we use to manage the Login
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class ShowUserDataActivity extends Activity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = ShowUserDataActivity.class.getName();

    /**
     * The action we use to manage the Registration
     */
    public static final String SHOW_USER_ACTION = Const.PKG + ".action.SHOW_USER_ACTION";

    /**
     * Extra used to get the UserModel
     */
    public static final String USER_EXTRA = Const.PKG + ".extra.USER_EXTRA";

    /**
     * The current UserModel
     */
    private UserModel mUserModel;

    /**
     * TextView for the Username
     */
    private TextView mUsername;

    /**
     * TextView for the Email
     */
    private TextView mEmail;

    /**
     * TextView for the BirthDate
     */
    private TextView mBirthDate;

    /**
     * TextView for the Location
     */
    private TextView mLocation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);
        // We get the UserModel
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }
        // We get the reference of the UI elements
        mUsername = (TextView) findViewById(R.id.username_value);
        mEmail = (TextView) findViewById(R.id.email_value);
        mBirthDate = (TextView) findViewById(R.id.birth_date_value);
        mLocation = (TextView) findViewById(R.id.location_value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Show username
        final String userName = mUserModel.getUsername();
        mUsername.setText(userName);
        // Show Email
        final String email = mUserModel.getEmail();
        if (!TextUtils.isEmpty(email)) {
            mEmail.setText(email);
        } else {
            mEmail.setText("-");
        }
        // We format the Date
        final DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mUserModel.getBirthDate());
        final String birthDate = format.format(calendar.getTime());
        mBirthDate.setText(birthDate);
        // Show Location
        final String location = mUserModel.getLocation();
        if (!TextUtils.isEmpty(location)) {
            mLocation.setText(location);
        } else {
            mLocation.setText("-");
        }
    }

    /**
     * This is invoked when we press the confirm button
     *
     * @param confirmButton The Button pressed
     */
    public void doConfirm(View confirmButton) {
        final Intent mainIntent = new Intent(this, MenuActivity.class);
        mainIntent.putExtra(MenuActivity.USER_EXTRA, mUserModel);
        startActivity(mainIntent);
        finish();
    }
}