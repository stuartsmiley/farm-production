package com.finca_la_caprichosa.jwt;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

/**
 * This is a singleton and not using CDI because the example I followed said
 * RsaJsonWebKey was not compatible with CDI.
 * TODO: after this is working try changing this to an application scoped bean?
 */
public class RsaKeyProducer {

    private volatile static RsaJsonWebKey rsaJsonWebKey;
    
    private RsaKeyProducer() {
        // forcing a singleton
    }

    public static RsaJsonWebKey produce() {
        if (rsaJsonWebKey == null)  {
            synchronized (RsaKeyProducer.class) {
                if (rsaJsonWebKey == null) {
                    createKey();
                }
            }
        }
        return rsaJsonWebKey;
    }

    private static void createKey() {
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            // no idea how to recover from this, so pass the buck
            throw new IllegalStateException("Something is terribly wrong", e);
        }
    }
}
