package com.qualitycyberservices.android.wordlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.qualitycyberservices.android.wordlist.db.Word;

import java.util.Locale;

/**
 * Created by b3nn on 4/1/17.
 */

public class WordDetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORD_ID = "com.qualitycyberservices.android.wordlist.word_id";
    private static final String DIALOG_DELETE_WORD = "delete_word";
    private static final String DIALOG_UPDATE_WORD = "update_word";

    private Long mWordId;
    private Word mWord;

    private TextView mIdTextView;
    private TextView mWordTextView;
    private TextView mDateTextView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_word_detail );

        mWordId = getIntent().getLongExtra( EXTRA_WORD_ID, 0 );

        mWord = WordDBManager.get( this ).getWordById( mWordId );

        mIdTextView = ( TextView )findViewById( R.id.id_text_view );
        mWordTextView = ( TextView )findViewById( R.id.word_text_view );
        mDateTextView = ( TextView )findViewById( R.id.date_text_view );

        mIdTextView.setText( String.format( Locale.getDefault(), "%d", mWord.getId() ) );
        mWordTextView.setText( mWord.getWord() );
        mDateTextView.setText( mWord.getDate().toString() );
    }

    public static Intent newIntent( Context packageContext, Long wordId ) {
        Intent i = new Intent( packageContext, WordDetailActivity.class );
        i.putExtra( EXTRA_WORD_ID, wordId );

        return i;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.activity_word_detail, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        switch ( item.getItemId() ) {

            case R.id.menu_item_delete_word:
                DeleteWordDialogFragment dialog = DeleteWordDialogFragment.newInstance( mWordId );
                dialog.show( getSupportFragmentManager(), DIALOG_DELETE_WORD );

                return true;

            case R.id.menu_item_update_word:
                UpdateWordDialogFragment dialog_two = UpdateWordDialogFragment.newInstance( mWordId );
                dialog_two.show( getSupportFragmentManager(), DIALOG_UPDATE_WORD );


            default:
                return super.onOptionsItemSelected( item );
        }
    }
}
