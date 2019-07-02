package core;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;

/**
 * Class BrithDateInformation
 *
 * @author Bryan & Dylan
 */
public class BirthDateInformation implements Information, Serializable {

    private static final long serialVersionUID = 354054054054L;

    private Date date;

    /**
     * Construct a BirthDateInformation
     *
     * @param birthdate birthdate
     */
    public BirthDateInformation(Date birthdate) {
        date = birthdate;
    }

    /**
     * get the age corresponding of the birthdate the time between the date of
     * birthdate and now
     *
     * @return a int the age of the birthdate
     */
    public int getAge() {

        Calendar curr = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(date);
        int yeardiff = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        curr.add(Calendar.YEAR, -yeardiff);
        if (birth.after(curr)) {
            yeardiff = yeardiff - 1;
        }
        return yeardiff;
    }

    /**
     * this information is public
     *
     * @return false
     */
    @Override
    public boolean isPrivate() {
        return false;
    }

    /**
     * this information type is BRITHDATE
     *
     * @return InformationType.BRITHDATE
     */
    @Override
    public InformationType getType() {
        return InformationType.BRITHDATE;
    }

    public Date getDate() {
        return this.date;
    }
}
