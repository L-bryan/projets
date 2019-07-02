package core;

import java.io.Serializable;

public class NameInformation implements Information, Serializable{

    private final String name;
    private final String firstname;

    public NameInformation(String name, String firsname) {
        this.name = name;
        this.firstname = firsname;
    }

    String getName() {
        return name;
    }

    String getFirsname() {
        return firstname;
    }
    
    
    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public InformationType getType() {
        return InformationType.NAME;
    }
    
}
