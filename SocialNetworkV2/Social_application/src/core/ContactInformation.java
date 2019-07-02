package core;

import java.io.Serializable;

/**
 * Class ContactInformation
 *
 * @author Bryan & Dylan
 */
public class ContactInformation implements Information, Serializable {

    private static final long serialVersionUID = 354054054054L;
    
    /**
     * The phonenumber
     */
    private final String tel;

    /**
     * The email address
     */
    private final String email;

    /**
     * Construct a contactInformation
     *
     * @param tel string a phone number
     * @param email string a email
     */
    public ContactInformation(final String tel, final String email) {
        this.tel = tel;
        this.email = email;
    }

    /**
     * get the phone number
     *
     * @return string the phone number
     */
    public String getTel() {
        return tel;
    }

    /**
     * get the email
     *
     * @return string the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * This information is private
     * @return the value of the information
     */
    @Override
    public boolean isPrivate() {
        return true;
    }
    
    /**
     * This information type is CONTACT
     * @return the type of information
     */
    @Override
    public InformationType getType() {
        return InformationType.CONTACT;
    }

}
