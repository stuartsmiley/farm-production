package com.finca_la_caprichosa.jwt;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

/**
 * Created by ssmiley on 7/9/17.
 */
public class JwtTokenUtility {

    public static String buildJwt(String subject) {
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
        JwtClaims claims = new JwtClaims();
        claims.setSubject(subject);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getRsaPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_PSS_USING_SHA256);

        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException e) {
            throw new IllegalStateException("problems with the JWT", e);
        }
        return jwt;
    }
}
