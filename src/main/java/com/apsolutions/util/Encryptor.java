package com.apsolutions.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encryptor {
    private static final String NAME_SECRET_KEY = "@Ap-Solutions-Key";
    private static final String ALGORITHM = "AES/ECB/PKCS5PADDING";

    public static String encrypt(String valueToEncrypt) {
        return execEncrypt(valueToEncrypt);
    }

    public static String encrypt(int valueToEncrypt) {
        return execEncrypt(String.valueOf(valueToEncrypt));
    }

    public static String decrypt(String valueEncrypted) {
        return execDecrypt(valueEncrypted);
    }

    private static String execEncrypt(String valueToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey());

            byte[] valueEncrypted = cipher.doFinal(valueToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(valueEncrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String execDecrypt(String valueEncrypted) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateSecretKey());

            byte[] valueDecrypted = cipher.doFinal(Base64.getDecoder().decode(valueEncrypted));
            return new String(valueDecrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKeySpec generateSecretKey() {
        byte[] keyEncrypted = NAME_SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyEncrypted = sha.digest(keyEncrypted);
            keyEncrypted = Arrays.copyOf(keyEncrypted, 16);

            return new SecretKeySpec(keyEncrypted, "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
