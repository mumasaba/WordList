package com.qualitycyberservices.android.wordlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.qualitycyberservices.android.wordlist.db.Word;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by b3nn on 4/12/17.
 * The Delete Word Dialog
 */

public class DeleteWordDialogFragment extends DialogFragment {
    private Long mWordId;
    private Word mWord;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        mWordId = getArguments().getLong( WordDetailActivity.EXTRA_WORD_ID );
        mWord = WordDBManager.get( getActivity() ).getWordById( mWordId );
    }

    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        return new AlertDialog.Builder( getActivity() ).setTitle( R.string.delete_word ).setMessage( R.string.dialog_message_two )
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        WordDBManager.get( getActivity() ).deleteWord( mWord );

                        Intent intent = new Intent( getActivity(), WordsActivity.class );
                        intent.addFlags( FLAG_ACTIVITY_CLEAR_TOP );
                        startActivity( intent );
                    }
                } )
                .setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        // User cancelled deleting word.
                    }
                } )
                .create();
    }

    public static DeleteWordDialogFragment newInstance( Long wordId ) {
        Bundle args = new Bundle();
        args.putLong( WordDetailActivity.EXTRA_WORD_ID, wordId );

        DeleteWordDialogFragment fragment = new DeleteWordDialogFragment();
        fragment.setArguments( args );

        return fragment;
    }
}
