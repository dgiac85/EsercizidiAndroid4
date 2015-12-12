package uk.co.massimocarli.android.ugho.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.massimocarli.android.ugho.R;

/**
 * Created by Massimo Carli on 15/06/13.
 */
public class RemoteDataFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = RemoteDataFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View remoteDataView = inflater.inflate(R.layout.fragment_remote_data, null);
        // We return the View for the fragment
        return remoteDataView;
    }

}
