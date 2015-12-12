package uk.co.massimocarli.android.ugho.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import uk.co.massimocarli.android.ugho.R;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.fragment.*;
import uk.co.massimocarli.android.ugho.model.UserModel;

/**
 * This is the Activity we use to show the different options of our application
 * after authentication
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class MenuActivity extends FragmentActivity implements MenuFragment.MenuFragmentListener {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = MenuActivity.class.getName();

    /**
     * The Tag for the MenuFragment
     */
    private static final String MENU_FRAGMENT_TAG = Const.PKG + ".tag.MENU_FRAGMENT_TAG";

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
        setContentView(R.layout.activity_single_fragment);
        // We get the UserModel
        this.mUserModel = (UserModel) getIntent().getParcelableExtra(USER_EXTRA);
        if (mUserModel == null) {
            Log.w(TAG_LOG, USER_EXTRA + " is mandatory!");
            finish();
        }
        if (savedInstanceState == null) {
            final MenuFragment fragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment, MENU_FRAGMENT_TAG).commit();
        }

    }


    /**
     * This is invoked when we press the newDataButton
     */
    @Override
    public void insertNewData() {
        Log.d(TAG_LOG, "We choose to insert new data");
        final Intent newDataIntent = new Intent(this, NewDataActivity.class);
        newDataIntent.putExtra(NewDataActivity.USER_EXTRA, mUserModel);
        startActivity(newDataIntent);
    }

    /**
     * This is invoked when we press the oldDataButton
     */
    @Override
    public void viewOldData() {
        Log.d(TAG_LOG, "We choose to view our old data");
        final Intent localDataIntent = new Intent(this, LocalDataActivity.class);
        startActivity(localDataIntent);
    }

    /**
     * This is invoked when we press the remoteDataButton
     */
    @Override
    public void viewRemoteData() {
        Log.d(TAG_LOG, "We choose to view remote data");
        final Intent remoteDataIntent = new Intent(this, RemoteDataActivity.class);
        startActivity(remoteDataIntent);
    }

}