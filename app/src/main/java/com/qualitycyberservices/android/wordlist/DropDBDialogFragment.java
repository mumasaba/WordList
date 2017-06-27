package com.qualitycyberservices.android.wordlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by b3nn on 4/12/17.
 */

public class DropDBDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        return new AlertDialog.Builder( getActivity() ).setTitle( R.string.drop_DB ).setMessage( R.string.dialog_message_one )
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        WordDBManager.get( getActivity() ).dropDatabase();
                    }
                } )
                .setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        // User cancelled dropping database.
                    }
                } )
                .create();
    }
}
