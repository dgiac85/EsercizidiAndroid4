package uk.co.massimocarli.android.ugho.model.ser;

import android.location.Location;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * This is the class that encaspulate the Model of the user for this application
 * <p/>
 * Created by Massimo Carli on 04/06/13.
 */
public class UserModel implements Serializable {

    /**
     * This is the serialVersionUID that the environment uses to chekc compatibility
     */
    private static final long serialVersionUID = 7526471155622776147L;

    /**
     * The username for this user.
     */
    private String mUsername;

    /**
     * The email of the user.
     */
    private String mEmail;

    /**
     * The birthDate of the user. To calculate the sign
     */
    private long mBirthDate;

    /**
     * The location of the user
     */
    private String mLocation;


    /**
     * Creates the UserModel with the mandatory information
     *
     * @param birthDate The birthDate of the user as a long
     */
    private UserModel(final long birthDate) {
        this.mBirthDate = birthDate;
    }

    /**
     * Creates a UserModel given its birthDate
     *
     * @param birthDate The birthDate of the user as a long
     * @return The UserModel instance for chaining
     */
    public static UserModel create(final long birthDate) {
        final UserModel userModel = new UserModel(birthDate);
        return userModel;
    }

    /**
     * Adds the username information to the UserModel
     *
     * @param username The username
     * @return The UserModel itself for chaining
     */
    public UserModel withUsername(final String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null here!");
        }
        this.mUsername = username;
        // We return the instance itself for chaining
        return this;
    }

    /**
     * Adds the email information to the UserModel
     *
     * @param email The user email
     * @return The UserModel itself for chaining
     */
    public UserModel withEmail(final String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null here!");
        }
        this.mEmail = email;
        // We return the instance itself for chaining
        return this;
    }

    /**
     * Adds the location information to the UserModel
     *
     * @param location The user location
     * @return The UserModel itself for chaining
     */
    public UserModel withLocation(final String location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null here!");
        }
        this.mLocation = location;
        // We return the instance itself for chaining
        return this;
    }

    /**
     * @return The username if any
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * @return The birthDate as a long
     */
    public long getBirthDate() {
        return this.mBirthDate;
    }

    /**
     * @return The user email if any
     */
    public String getEmail() {
        return this.mEmail;
    }

    /**
     * @return The location of the user if any
     */
    public String getLocation() {
        return this.mLocation;
    }

    /**
     * @return True if the user is anonymous and false otherwise.
     */
    public boolean isAnonymous() {
        return TextUtils.isEmpty(this.mUsername);
    }

    /**
     * @return True if the user is logged and false otherwise.
     */
    public boolean isLogged() {
        return !TextUtils.isEmpty(this.mUsername);
    }

}
