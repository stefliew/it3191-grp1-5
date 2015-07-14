package entity;

import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
//import org.apache.commons.codec.binary.Base64;

public class Password {
    // The higher the number of iterations the more 
    // expensive computing the hash is for us and
    // also for an attacker.
	
	// number of times hashing is iterated
    private static final int iterations = 20*1000;
    
    //saltLen is in bytes
    private static final int saltLen = 32;
    
    //desiredKeyLen is in bits
    private static final int desiredKeyLen = 256;
    

    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database. 
        Empty passwords are not supported. */
    
    public static String getSaltedHash(String password) throws Exception {
    	
    	// generate salt with SHA1 RNG
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        
        // concatenate and store the salt with the hashed password, $ as delimiter
        return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds 
        to a stored salted hash of the password. */
    
    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                "The stored password have the form 'salt$hash'");
            // only happens if database is modified directly
        }
        // hashes the password with the salt
        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));
        
        // return true if password matches
        return hashOfInput.equals(saltAndPass[1]);
    }
    
    // using PBKDF2
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        	// only happens if validation is not done properly
        
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}