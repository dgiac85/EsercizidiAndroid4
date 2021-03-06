package uk.co.massimocarli.android.ugho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import uk.co.massimocarli.android.ugho.conf.Const;
import uk.co.massimocarli.android.ugho.model.UserModel;

/**
 * This is the Activity we use to choose a topic to insert data
 * <p/>
 * Created by Massimo Carli on 31/05/2013.
 */
public class NewDataActivity extends Activity {

    /**
     * The Tag of the Log for this class
     */
    private static final String TAG_LOG = NewDataActivity.class.getName();

    /**
     * Extra used to get the UserModel
     */
    public static final String USER_EXTRA = Const.PKG + ".extra.USER_EXTRA";

    /**
     * Id for the input data request
     */
    private static final int INPUT_DATA_REQUEST_ID = 1;

    /**
     * The current UserModel
     */
    private UserModel mUserModel;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
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
        final Intent questionIntent = new Intent(this, InputDataActivity.class);
        String questionType = null;
        switch (newDataButton.getId()) {
            case R.id.new_love_data:
                questionType = getResources().getString(R.string.love_label);
                break;
            case R.id.new_health_data:
                questionType = getResources().getString(R.string.health_label);
                break;
            case R.id.new_work_data:
                questionType = getResources().getString(R.string.work_label);
                break;
            case R.id.new_luck_data:
                questionType = getResources().getString(R.string.luck_label);
                break;
        }
        questionIntent.putExtra(InputDataActivity.INPUT_TYPE_EXTRA, questionType);
        startActivityForResult(questionIntent, INPUT_DATA_REQUEST_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INPUT_DATA_REQUEST_ID && resultCode == RESULT_OK) {
            // Here we'll manage the data from the questions
            // TODO Manage data from the questions
        }
    }
}