package com.qualitycyberservices.android.wordlist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.qualitycyberservices.android.wordlist.db.Word;

/**
 * Created by b3nn on 6/24/17.
 * The Update Word Dialog.
 */

public class UpdateWordDialogFragment extends DialogFragment {
    private EditText mUpdatedWordField;
    private Long mWordId;
    private Word mWord;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        mWordId = getArguments().getLong( WordDetailActivity.EXTRA_WORD_ID );
        mWord = WordDBManager.get( getActivity() ).getWordById( mWordId );
    }

    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        View v = getActivity().getLayoutInflater().inflate( R.layout.dialog_update_word, new ConstraintLayout( getActivity() ) );

        mUpdatedWordField = ( EditText )v.findViewById( R.id.updated_word );

        return new AlertDialog.Builder( getActivity() ).setTitle( R.string.word_update).setView( v )
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        String input = mUpdatedWordField.getText().toString().trim();

                        if ( input.length() != 0 ) {
                            mWord.setWord( input );
                            WordDBManager.get( getActivity() ).updateWord( mWord );
                        }
                    }
                } )
                .setNegativeButton( android.R.string.cancel, null )
                .create();
    }

    public static UpdateWordDialogFragment newInstance( Long wordId ) {
        Bundle args = new Bundle();
        args.putLong( WordDetailActivity.EXTRA_WORD_ID, wordId );

        UpdateWordDialogFragment fragment = new UpdateWordDialogFragment();
        fragment.setArguments( args );

        return fragment;
    }
}
