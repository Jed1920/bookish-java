package org.softwire.training.bookish.models.database.Services;

import org.softwire.training.bookish.models.database.Models.User;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends DatabaseService {

    public boolean assertUser(User user) {
        String salt = jdbi.withHandle(handle -> handle.createQuery("SELECT salt FROM password_salts WHERE username =:username")
                .bind("username", user.getUsername())
                .mapTo(String.class).findOnly());
        String saltedPassword = user.getPassword() + salt;
        String newHashedPassword = get_SHA_1_SecurePassword(saltedPassword);
        String hashedPassword = jdbi.withHandle(handle -> handle.createQuery("SELECT hash_password FROM users WHERE username =:username")
                .bind("username", user.getUsername())
                .mapTo(String.class).findOnly());
        return (newHashedPassword.equals(hashedPassword));
    }

    public boolean addUser(User newUser) throws NoSuchAlgorithmException {
        if(!isUsernameAvailable(newUser.getUsername())) {
            return false;
        }
            String salt = getSalt();
            String saltedPassword = newUser.getPassword() + salt;
            String newHashedPassword = get_SHA_1_SecurePassword(saltedPassword);
            jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO users(username,hash_password) VALUES (:username,:password);")
                    .bind("username", newUser.getUsername())
                    .bind("password", newHashedPassword)
                    .execute());
            jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO password_salts(username,salt) VALUES (:username,:salt);")
                    .bind("username", newUser.getUsername())
                    .bind("salt", salt)
                    .execute());
            return assertUser(newUser);
        }

    private boolean isUsernameAvailable(String username){
        try {
            String storedUsername = jdbi.withHandle(handle -> handle.createQuery("SELECT username FROM users WHERE (username =:username)")
                    .bind("username", username)
                    .mapTo(String.class).findOnly());
        return false;
        } catch (Exception e){
            return true;
        }
    }


    private static String get_SHA_1_SecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

}

