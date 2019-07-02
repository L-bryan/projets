package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profil extends Node implements Serializable{

    private final List<Information> information;
    private Preference preference;

    //verifier que info de base s'y trouve
    public Profil(int id, Information... info) {
        super(id);
        information = new ArrayList<>();
        addInformation(info);
        int count = 0;
        for(Information i : information){
            if(i.getType() == InformationType.NAME ||
               i.getType() == InformationType.BRITHDATE ||
               i.getType() == InformationType.SEX
            ){
                count++;
            }
        }
        if(count<3){
            throw new IllegalArgumentException(
                    "One of them is missing : \n"+
                    "InformationName, InformationBrithDate, InformationSex"
            );
        }

        //this.preference = null;
    }
    
    List<Information> getInformation() {
        return information;
    }

    final void addInformation(Information... info) {
        for (Information i : info) {
            if (i.getType() == InformationType.PREFERENCE) {
                this.preference = (Preference) i;
            } else {
                this.information.add(i);
            }

        }
    }

    Preference getPreference() {
        return preference;
    }

}
