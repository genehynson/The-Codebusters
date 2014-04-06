package com.utility.buckaroos;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class designed to provide a hashed version of the password for security.
 *
 * @author Jordan LeRoux
 * @version 1.0
 */
public class PasswordHash {

    public static int PW_HASH_ITERATION_COUNT = 5000;
    private static MessageDigest md;
    private final int toConvert = 0xff;

    /**
     * Constructs a password hash object.
     */
    public PasswordHash() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hashes the password passed in.
     *
     * @param pw The password.
     * @return The hashed password.
     */
    public String hashPassword(String pw) {
        String salt = "rjelk;a903u29ujmfadkls;09432ujop;zad";
        byte[] bSalt;
        byte[] bPw;

        try {
            bSalt = salt.getBytes("UTF-8");
            bPw = pw.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported Encoding", e);
        }

        byte[] digest = run(bPw, bSalt);
        for (int i = 0; i < PW_HASH_ITERATION_COUNT - 1; i++) {
            digest = run(digest, bSalt);
        }

        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & toConvert)));
        }

        return sb.toString();
    }

    /**
     * Runs the md5 password encryption.
     *
     * @param input The input for conversion.
     * @param salt
     * @return The encrypted password
     */
    private static byte[] run(byte[] input, byte[] salt) {
        md.update(input);
        return md.digest(salt);
    }

}
