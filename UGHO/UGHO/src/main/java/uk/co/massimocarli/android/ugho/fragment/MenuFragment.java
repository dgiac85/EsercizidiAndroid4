package uk.co.massimocarli.android.ugho.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.massimocarli.android.ugho.R;

/**
 * Created by Massimo Carli on 15/06/13.
 */
public class MenuFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = MenuFragment.class.getName();

    /**
     * Interface that should be implemented by the activity that uses this Fragment
     */
    public interface MenuFragmentListener {

        /**
         * Invoked to notify the choice of the user to insert a new data
         */
        void insertNewData();

        /**
         * Invoked to notify the choice of the user to view local data
         */
        void viewOldData();

        /**
         * Invoked to notify the choice of the user to view remote data
         */
        void viewRemoteData();

    }

    /**
     * The reference to the listener of this Fragment
     */
    private MenuFragmentListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MenuFragmentListener) {
            mListener = (MenuFragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View menuView = inflater.inflate(R.layout.fragment_menu, null);
        // We note that we don't care about the Button type because the onClick is an event
        // of all the View
        menuView.findViewById(R.id.insert_new_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "User requests to insert a new data!");
                    mListener.insertNewData();
                }
            }
        });
        // Old local data
        menuView.findViewById(R.id.view_old_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "User requests to view local data!");
                    mListener.viewOldData();
                }
            }
        });
        // Remote data
        menuView.findViewById(R.id.view_remote_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "User requests to view remote data!");
                    mListener.viewRemoteData();
                }
            }
        });
        // We return the View for the fragment
        return menuView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
