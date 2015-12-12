package uk.co.massimocarli.android.ugho.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import uk.co.massimocarli.android.ugho.R;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.fragment.LocalDataFragment;
import uk.co.massimocarli.android.ugho.fragment.RemoteDataFragment;

/**
 * This is the Activity we use to show remote data
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class RemoteDataActivity extends FragmentActivity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = RemoteDataActivity.class.getName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        // We add the Fragment if not already done
        if (savedInstanceState == null) {
            final RemoteDataFragment fragment = new RemoteDataFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.anchor_point, fragment).commit();
        }
    }


}