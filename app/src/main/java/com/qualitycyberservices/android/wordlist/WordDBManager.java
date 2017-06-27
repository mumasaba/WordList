package com.qualitycyberservices.android.wordlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.qualitycyberservices.android.wordlist.db.DaoMaster;
import com.qualitycyberservices.android.wordlist.db.DaoSession;
import com.qualitycyberservices.android.wordlist.db.Word;
import com.qualitycyberservices.android.wordlist.db.WordDao;

import java.util.List;

/**
 * Created by b3nn on 3/12/17.
 * The Database management class.
 */

public class WordDBManager {
    private static WordDBManager sWordDBManager;
    private Context mAppContext;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase mDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static final String DB_NAME = "word_db";

    public static WordDBManager get( Context context ) {

        if ( sWordDBManager == null ) {
            sWordDBManager = new WordDBManager( context.getApplicationContext() );
        }

        return sWordDBManager;
    }

    private WordDBManager( Context appContext ) {
        mAppContext = appContext;
        mHelper = new DaoMaster.DevOpenHelper( mAppContext, DB_NAME, null );
    }

    public void openReadableDB() {
        mDatabase = mHelper.getReadableDatabase();
        mDaoMaster = new DaoMaster( mDatabase );
        mDaoSession = mDaoMaster.newSession();
    }

    public void openWritableDB() {
        mDatabase = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster( mDatabase );
        mDaoSession = mDaoMaster.newSession();
    }

    public void insertWord( Word word ) {

        if ( word != null ) {
            openWritableDB();
            WordDao wordDao = mDaoSession.getWordDao();
            wordDao.insert( word );
            Toast.makeText( mAppContext, mAppContext.getString( R.string.inserted_toast, word.getWord() ), Toast.LENGTH_SHORT ).show();
            mDaoSession.clear();
        }
    }

    public List< Word > getWords() {
        List< Word > words;
        openReadableDB();
        WordDao wordDao = mDaoSession.getWordDao();
        words = wordDao.loadAll();
        mDaoSession.clear();
        return words;
    }

    public void dropDatabase() {
        openWritableDB();
        DaoMaster.dropAllTables( mDatabase, true );
        mHelper.onCreate( mDatabase );
    }

    public void deleteWord( Word word ) {
        openWritableDB();
        WordDao wordDao = mDaoSession.getWordDao();
        wordDao.delete( word );
        mDaoSession.clear();
    }

    public Word getWordById( Long wordId ) {
        Word word;
        openReadableDB();
        WordDao wordDao = mDaoSession.getWordDao();
        word = wordDao.load( wordId );
        mDaoSession.clear();

        return word;
    }

    public void updateWord( Word word ) {
        openWritableDB();
        WordDao wordDao = mDaoSession.getWordDao();
        wordDao.update( word );
        mDaoSession.clear();
    }
}
