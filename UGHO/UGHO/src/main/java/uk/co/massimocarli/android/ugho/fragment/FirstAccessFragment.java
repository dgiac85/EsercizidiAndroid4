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
public class FirstAccessFragment extends Fragment {

    /**
     * The tag for the log
     */
    private static final String TAG_LOG = FirstAccessFragment.class.getName();

    /**
     * Interface that should be implemented by the activity that uses this Fragment
     */
    public interface FirstAccessListener {

        /**
         * Invoked to notify the choice of the user to enter as anonymous
         */
        void enterAsAnonymous();

        /**
         * Invoked to notify the choice of the user to login
         */
        void doLogin();

        /**
         * Invoked to notify the choice of the user to register
         */
        void doRegistration();

    }

    /**
     * The reference to the listener of this Fragment
     */
    private FirstAccessListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FirstAccessListener) {
            mListener = (FirstAccessListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We create the layout for this fragment
        final View firstAccessView = inflater.inflate(R.layout.fragment_main, null);
        // We note that we don't care about the Button type because the onClick is an event
        // of all the View
        firstAccessView.findViewById(R.id.anonymous_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "User requests to enter as anonymous!");
                    mListener.enterAsAnonymous();
                }
            }
        });
        // Login
        firstAccessView.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "User requests to login!");
                    mListener.doLogin();
                }
            }
        });
        // Registration
        firstAccessView.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    Log.d(TAG_LOG, "User requests to register!");
                    mListener.doRegistration();
                }
            }
        });
        // We return the View for the fragment
        return firstAccessView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
