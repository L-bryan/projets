package core;

import java.io.Serializable;

/**
 *
 * @author
 */
class ContactInformation implements Information, Serializable{

    private String tel;
    private String email;

    public ContactInformation(String tel, String email) {
        this.tel = tel;
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public InformationType getType() {
        return InformationType.CONTACT;
    }
    
}
