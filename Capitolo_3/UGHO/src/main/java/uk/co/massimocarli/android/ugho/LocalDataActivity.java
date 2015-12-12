package uk.co.massimocarli.android.ugho;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import uk.co.massimocarli.android.ugho.conf.Const;

/**
 * This is the Activity we use to show local data
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class LocalDataActivity extends Activity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = LocalDataActivity.class.getName();

    /**
     * The name of the extra that will contain the type of question to do
     */
    public static final String INPUT_TYPE_EXTRA = Const.PKG + ".extra.INPUT_TYPE_EXTRA";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);
    }


}