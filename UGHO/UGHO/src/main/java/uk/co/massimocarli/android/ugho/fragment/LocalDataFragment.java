package uk.co.massimocarli.android.ugho.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import uk.co.massimocarli.android.ugho.R;
import uk.co.massimocarli.android.ugho.conf.Const;

/**
 * Created by Massimo Carli on 15/06/13.
 */
public class LocalDataFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = LocalDataFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View localDataView = inflater.inflate(R.layout.fragment_local_data, null);
        // We return the View for the fragment
        return localDataView;
    }

}
