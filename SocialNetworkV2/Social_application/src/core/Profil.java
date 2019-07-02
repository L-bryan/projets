package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Profil 
 * @author Bryan & Dylan
 */
public class Profil extends Node implements Serializable{
    private static final long serialVersionUID = 354054054054L;

    /**
     * All informations of a person
     */
    private final List<Information> information;


    /**
     * Construct a Profil
     * @param idProfil int id of the Profil
     * @param informations vararg of Information
     * @throws IllegalArgumentException if there's not 
     * a Name & BrithDate & SEX Information type
     */
    public Profil(final int idProfil, final Information... informations) {
        super(idProfil);
        information = new ArrayList<>();
        int count = 0;
        
        for(final Information infoProfil : informations){
            if(infoProfil.getType() == InformationType.NAME ||
               infoProfil.getType() == InformationType.BRITHDATE ||
               infoProfil.getType() == InformationType.SEX
            ){
                
                count++;
            }
            information.add(infoProfil);
            if(infoProfil.getType()==InformationType.PREFERENCE){
                if(((PreferenceInformation)infoProfil).getAgeMin()==0 && ((PreferenceInformation)infoProfil).getAgeMax()==0){
                    information.remove(infoProfil);
                }
            }
            
        }
        if(count<3){
            throw new IllegalArgumentException(
                    "One of them is missing : \n"+
                    "InformationName, InformationBrithDate, InformationSex"
            );
        }
    }
    
    /**
     * add Information for a Profil
     * @param informations vararg of information
     */
    public void addInformation(final Information... informations) {
        for (final Information infoProfil : informations) {   
            if(isTypeExist(infoProfil.getType())&& infoProfil.getType()!= InformationType.HOBBIE){
                throw new IllegalArgumentException("This information is already exist");
            }
            this.information.add(infoProfil);
        }
    }
    
    /**
     * Check if a type is already exist
     * @param type A type of information to check
     * @return true if the type exists.
     */
    public boolean isTypeExist(final InformationType type){
        Boolean exist=false;
        for(final Information infoProfil : information){
            if(infoProfil.getType()==type){
                exist=true;
                break;
            }
        }
        return exist;
    }
    
    /**
     * Get all infomration about a profil
     * @return information
     */
    public List<Information> getInformation(){
        return this.information;
    }
/**
     * get the Name information for this Profil
     * @return a NameInformation 
     */
    public NameInformation getNameInformation(){
        NameInformation name = null;
        for(final Information infoProfil : information){
            if(infoProfil.getType() == InformationType.NAME){
                name = (NameInformation) infoProfil;
                break;
            }
        }
        
        return name;
    }
    
    /**
     * get the NameInformation to string
     * full name is 'firstname + space + family name' like "Guy vanbels"
     * @return string the nameInformation concatenation
     */
    public String getStringFullName(){
        final NameInformation name = getNameInformation();
        return (name.getFirstname() + " "+ name.getName());
    }
    
    /**
     * get the BirthDateInformation for this Profil
     * @return BirthDateInformation
     */
    public BirthDateInformation getBirthDateInformation(){
        BirthDateInformation bdi = null;
        for(final Information i : information){
            if(i.getType() == InformationType.BRITHDATE){
                bdi = (BirthDateInformation) i;
                break;
            }
        }
        
        return bdi;
    }
    
    /**
     * get the SexInformation for this Profil
     * @return SexInformation
     */
    public SexInformation getSexInformation(){
        SexInformation sex = null;
        for(final Information infoProfil : information){
            if(infoProfil.getType() == InformationType.SEX){
                sex = (SexInformation) infoProfil;
                break;
            }
        }
        
        return sex;
    }
    
    /**
     * get the ContactInformation for this Profil
     * @return ContactInformation
     */
    public ContactInformation getContactInformation(){
        ContactInformation contact = null;
        for(final Information infoProfil : information){
            if(infoProfil.getType() == InformationType.CONTACT){
                contact = (ContactInformation) infoProfil;
                break;
            }
        }
        
        return contact;
    }
    
    /**
     * get the email from ContactInformation
     * @return String email
     */
    public String getStringEmail(){
        final ContactInformation contact = getContactInformation();
        String email = "";
        if(contact != null){
            email = contact.getEmail();
        }    
        return email;
    }
    
    /**
     * get the phone number from ContactInformation
     * @return String phone number
     */
    public String getStringTel(){
        final ContactInformation contact = getContactInformation();
        String tel = "";
        if(contact != null){
            tel = contact.getTel();
        }
        return tel;
    }
    
    /**
     * get the List of all HobbieInformation for this Profil
     * @return List of HobbieInformation
     */
    public List<HobbieInformation> getHobbieInformation(){
        final List<HobbieInformation> hobbies = new ArrayList<>();
        for(final Information infoProfil : information){
            if(infoProfil.getType() == InformationType.HOBBIE){
                hobbies.add((HobbieInformation) infoProfil);
            }
        }
        return hobbies;
    }
    
    /**
     * Get all information of type Preference
     * @return inofmration with type Prefernce
     */
    public PreferenceInformation getPreference(){
        PreferenceInformation preference = null;
        for(final Information infoProfil : information){
            if(infoProfil.getType() == InformationType.PREFERENCE){
                preference = (PreferenceInformation) infoProfil;
                break;
            }
        }
        
        return preference;
    }

}
