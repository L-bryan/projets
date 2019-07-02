package core;

import java.io.Serializable;

/**
 *
 * @author Lyan
 */
public class SexInformation implements Information,Serializable{

    private Sex sex;

    public SexInformation(Sex sex) {
        this.sex = sex;
    }

    Sex getSex() {
        return sex;
    }
    
    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public InformationType getType() {
        return InformationType.SEX;
    }
    
}
