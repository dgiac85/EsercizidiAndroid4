package uk.co.massimocarli.android.ugho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.model.UserModel;

/**
 * This is the Activity we use to show the different options of our application
 * after authentication
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class MenuActivity extends Activity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = MenuActivity.class.getName();

    /**
     * Extra used to get the UserModel
     */
    public static final String USER_EXTRA = Const.PKG + ".extra.USER_EXTRA";

    /**
     * The current UserModel
     */
    private UserModel mUserModel;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // We get the UserModel
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }
    }

    /**
     * This is invoked when we press the newDataButton
     *
     * @param newDataButton The Button pressed
     */
    public void insertNewData(View newDataButton) {
        Log.d(TAG_LOG, "We choose to insert new data");
        final Intent newDataIntent = new Intent(this, NewDataActivity.class);
        newDataIntent.putExtra(NewDataActivity.USER_EXTRA, mUserModel);
        startActivity(newDataIntent);
    }

    /**
     * This is invoked when we press the oldDataButton
     *
     * @param oldDataButton The Button pressed
     */
    public void viewOldData(View oldDataButton) {
        Log.d(TAG_LOG, "We choose to view our old data");
        final Intent localDataIntent = new Intent(this, LocalDataActivity.class);
        startActivity(localDataIntent);
    }

    /**
     * This is invoked when we press the remoteDataButton
     *
     * @param remoteDataButton The Button pressed
     */
    public void viewRemoteData(View remoteDataButton) {
        Log.d(TAG_LOG, "We choose to view remote data");
        final Intent remoteDataIntent = new Intent(this, RemoteDataActivity.class);
        startActivity(remoteDataIntent);
    }


}