package core;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Lyan
 */
public class BirthDateInformation  implements Information, Serializable{
    private final int day;
    private final int month;
    private final int year;

    public BirthDateInformation(int day, int month, int year) {
        
        this.day = day;
        this.month = month;
        this.year = year;
        
        if(month<=0 || month>12){
            throw new IllegalArgumentException("month not in 1-12");
        }
        if(day<=0 || day> dayNumberInMonth() ){
            throw new IllegalArgumentException("day not in 1-[28-29][30-31] ");
        }
    }
    
    boolean isLeapYear(){
        return ((year%4==0 && !(year%100==0))|| year%400==0);
    }
    
    int dayNumberInMonth(){  
        int dayNumberInMonth;
        switch (month) {
        case 2:
            if(isLeapYear()){
                dayNumberInMonth = 28;
            }else{
                dayNumberInMonth = 29;
            }
            break;
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            dayNumberInMonth = 31;
        default:
            dayNumberInMonth = 30;
            break;
        }
        return dayNumberInMonth;
    }

    int getDay() {
        return day;
    }

    int getMonth() {
        return month;
    }

    int getYear() {
        return year;
    }

    int getAge(){
        Calendar now = Calendar.getInstance();
        int age = year - now.get(Calendar.YEAR);
        if(month > now.get(Calendar.MONTH)){
            age--;
        }else if ( month == now.get(Calendar.MONTH)){
            if(day > now.get(Calendar.DAY_OF_MONTH)){
                age--;
            }
        }
        return age;
    }
    
    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public InformationType getType() {
        return InformationType.BRITHDATE;
    }
    
}
