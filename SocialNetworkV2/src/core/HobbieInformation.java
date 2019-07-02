package core;

import java.io.Serializable;

public class HobbieInformation implements Information, Serializable{
    private String name;

    public HobbieInformation(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
    
    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public InformationType getType() {
        return InformationType.HOBBIE;
    }
}
