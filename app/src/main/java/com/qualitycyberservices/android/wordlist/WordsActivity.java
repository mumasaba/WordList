package com.qualitycyberservices.android.wordlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qualitycyberservices.android.wordlist.db.Word;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by b3nn on 3/24/17.
 * Activity displaying the words in our database. The words are displayed in a RecyclerView.
 */

public class WordsActivity extends AppCompatActivity {
    private RecyclerView mWordRecyclerView;
    private WordAdapter mAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_words );

        mWordRecyclerView = ( RecyclerView )findViewById( R.id.word_recycler_view );
        mWordRecyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        updateUI();
    }

    private class WordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Word mWord;
        private TextView mWordTextView;
        private TextView mDateTextView;

        public WordHolder( View itemView ) {
            super( itemView );
            itemView.setOnClickListener( this );

            mWordTextView = ( TextView )itemView.findViewById( R.id.list_item_word_word_text_view );
            mDateTextView = ( TextView )itemView.findViewById( R.id.list_item_word_date_text_view );
        }

        public void bindWord( Word word ) {
            mWord = word;
            mWordTextView.setText( mWord.getWord() );

            DateFormat df = SimpleDateFormat.getDateInstance();
            mDateTextView.setText( df.format( mWord.getDate() ) );
        }

        @Override
        public void onClick( View view ) {
            Long wordId = mWord.getId();
            Intent intent = WordDetailActivity.newIntent( WordsActivity.this, wordId );
            startActivity( intent );
        }
    }

    private class WordAdapter extends RecyclerView.Adapter< WordHolder > {
        private List< Word > mWords;

        public WordAdapter( List< Word > words ) {
            mWords = words;
        }

        @Override
        public WordHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate( R.layout.list_item_word, parent, false );

            return new WordHolder( view );
        }

        @Override
        public void onBindViewHolder( WordHolder holder, int position ) {
            Word word = mWords.get( position );
            holder.bindWord( word );
        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }
    }

    private void updateUI() {
        WordDBManager wordDBManager = WordDBManager.get( this );
        List< Word > words = wordDBManager.getWords();

        if ( mAdapter == null ) {
            mAdapter = new WordAdapter( words );
            mWordRecyclerView.setAdapter( mAdapter );
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    protected void onResume() {
        super.onResume();
        updateUI();
    }

    public static Intent newIntent( Context packageContext ) {
        return new Intent( packageContext, WordsActivity.class );
    }
}
