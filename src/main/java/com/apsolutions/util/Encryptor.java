package com.apsolutions.util;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Encryptor {
    private static final String NAME_SECRET_KEY = "@Ap-Solutions-Key";

    public static String encrypt(String valueToEncrypt) {
        return execEncrypt(valueToEncrypt);
    }

    public static String encrypt(int valueToEncrypt) {
        return execEncrypt(String.valueOf(valueToEncrypt));
    }

    public static String encryptToUri(String valueToEncrypt) {
        return execEncryptToUri(valueToEncrypt);
    }

    public static String encryptToUri(int valueToEncrypt) {
        return execEncryptToUri(String.valueOf(valueToEncrypt));
    }

    public static String decrypt(String valueEncrypted) {
        return execDecrypt(valueEncrypted);
    }

    public static String decryptFromUri(String valueEncrypted) {
        return execDecryptFromUri(valueEncrypted);
    }

    private static String execEncrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey());
            byte[] valueBytesEncrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return new String(valueBytesEncrypted, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static String execEncryptToUri(String value) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey());
            byte[] valueBytesEncrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return new String(valueBytesEncrypted, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static String execDecrypt(String valueEncrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, generateSecretKey());
            byte[] valueBytesDecrypted = cipher.doFinal(valueEncrypted.getBytes(StandardCharsets.UTF_8));
            return new String(valueBytesDecrypted, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static String execDecryptFromUri(String valueEncrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, generateSecretKey());
            byte[] valueBytesDecrypted = cipher.doFinal(valueEncrypted.getBytes(StandardCharsets.UTF_8));
            return new String(valueBytesDecrypted, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKey generateSecretKey() {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            KeySpec spec = new PBEKeySpec(NAME_SECRET_KEY.toCharArray(), salt, 65536, 256);
            return factory.generateSecret(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
