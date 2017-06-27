package com.qualitycyberservices.android.gdaogen;

import java.util.UUID;

/**
 * Created by b3nn on 3/12/17.
 */

public class UUIDConverter implements PropertyConverter< UUID, String > {

    @Override
    public UUID convertToEntityProperty( String databaseValue ) {

        if ( databaseValue == null ) {
            UUID id = UUID.randomUUID();
            return id;
        }
        else
            return UUID.fromString( databaseValue );
    }

    @Override
    public String convertToDatabaseValue( UUID entityProperty ) {

        if ( entityProperty == null ) {
            entityProperty = UUID.randomUUID();

            return entityProperty.toString();
        }

        return entityProperty.toString();
    }
}
