package com.qualitycyberservices.android.wordlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qualitycyberservices.android.wordlist.db.Word;

public class WordListActivity extends AppCompatActivity {
    EditText mEditText;
    Button mButton;
    private static final String DIALOG_DROP_DATABASE = "drop_database";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_word_list );

        mEditText = ( EditText )findViewById( R.id.editText );
        mButton = ( Button )findViewById( R.id.button );
        mButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String string = mEditText.getText().toString().trim();

                if ( string.length() != 0 ) {
                    Word word = new Word();
                    word.setWord( string );
                    WordDBManager.get( WordListActivity.this ).insertWord( word );
                    mEditText.getText().clear();
                }
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.activity_word_list, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        switch ( item.getItemId() ) {

            case R.id.menu_item_list_words:
                Intent i = new Intent( this, WordsActivity.class );
                startActivity( i );

                return true;

            case R.id.menu_item_drop_database:
                DropDBDialogFragment dialog = new DropDBDialogFragment();
                dialog.show( getSupportFragmentManager(), DIALOG_DROP_DATABASE );

                return true;
            
            default:
                return super.onOptionsItemSelected( item );
        }
    }
}
