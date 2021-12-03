package com.shubhankar.debita.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encrypt {
    private static final int workload = 12;

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }

    public static Boolean checkPassword(String storedHash, String plaintextPassword) {
        return BCrypt.checkpw(plaintextPassword, storedHash);
    }
}
