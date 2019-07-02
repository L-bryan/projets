package core;

import java.io.Serializable;

/**
 *
 * @author Lyan
 */
class Preference implements Information, Serializable{
    private SexInformation sex; //pas final car modifiable
    private int friend_distance;
    private int age_min;
    private int age_max;

    Preference(SexInformation sex, int friend_distance, 
            int age_min, int age_max) {
        this.sex = sex;
        this.friend_distance = friend_distance;
        this.age_min = age_min;
        this.age_max = age_max;
    }

    SexInformation getSex() {
        return sex;
    }

    int getFriend_distance() {
        return friend_distance;
    }

    int getAge_min() {
        return age_min;
    }

    int getAge_max() {
        return age_max;
    }
    
    @Override
    public boolean isPrivate() {
        return true;//pas certain
    }

    @Override
    public InformationType getType() {
        return InformationType.PREFERENCE;
    }
    
}
