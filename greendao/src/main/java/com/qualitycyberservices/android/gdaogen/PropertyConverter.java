package com.qualitycyberservices.android.gdaogen;

/**
 * Created by b3nn on 3/12/17.
 */

public interface PropertyConverter< P, D > {
    P convertToEntityProperty( D databaseValue );

    D convertToDatabaseValue( P entityProperty );
}
