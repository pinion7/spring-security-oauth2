package com.htbeyond.utils

import com.nimbusds.jose.jwk.Curve
import com.nimbusds.jose.jwk.ECKey
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.RSAKey
import java.io.FileInputStream
import java.security.KeyPair
import java.security.KeyStore
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.UUID


class Jwks {

    companion object {
        fun generateRsa(): RSAKey {
            val keyPair: KeyPair = KeyGeneratorUtils.generateRsaKey()
            val publicKey: RSAPublicKey = keyPair.public as RSAPublicKey
            val privateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey
            // @formatter:off
            return RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build()
            // @formatter:on
        }

        fun generateEc(): ECKey {
            val keyPair: KeyPair = KeyGeneratorUtils.generateEcKey()
            val publicKey: ECPublicKey = keyPair.public as ECPublicKey
            val privateKey: ECPrivateKey = keyPair.private as ECPrivateKey
            val curve: Curve = Curve.forECParameterSpec(publicKey.params)
            // @formatter:off
            return ECKey.Builder(curve, publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build()
            // @formatter:on
        }

        fun generateSecret(): OctetSequenceKey {
            val secretKey = KeyGeneratorUtils.generateSecretKey()
            // @formatter:off
            return OctetSequenceKey.Builder(secretKey)
                .keyID(UUID.randomUUID().toString())
                .build()
            // @formatter:on
        }

        fun buildJWKSet(): JWKSet {
            val keyStore = KeyStore.getInstance("pkcs12")
            FileInputStream("src/main/resources/huongdanjava.pfx").use { fis ->
                keyStore.load(fis, "123456".toCharArray())
                return JWKSet.load(keyStore) { "123456".toCharArray() }
            }
        }
    }
}