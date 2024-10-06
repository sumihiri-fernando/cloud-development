package se.sumihiri.aplicationdb.configurations;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

public class Config {
    private static CognitoIdentityProviderClient getClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }


}
