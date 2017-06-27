package com.qualitycyberservices.android.gdaogen;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDAOGenerator {
    private static final String PROJECT_DIR = System.getProperty( "user.dir" );
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main( String args[] ) throws Exception {
        Schema schema = new Schema( 1, "com.qualitycyberservices.android.wordlist.db" ); // .db: subpackage where you want your database entities to reside
        schema.enableKeepSectionsByDefault();

        addTables( schema );

        new DaoGenerator().generateAll( schema, OUT_DIR );
    }

    private static void addTables( Schema schema ) {
        Entity word = addWord( schema );
    }

    private static Entity addWord( Schema schema ) {
        Entity word = schema.addEntity( "Word" );
        word.addIdProperty().primaryKey().autoincrement();
        word.addStringProperty( "UUID" ).customType( "java.util.UUID", "com.qualitycyberservices.android.gdaogen.UUIDConverter" ).notNull();
        word.addStringProperty( "word" );
        word.addDateProperty( "date" ).notNull();

        return word;
    }
}
