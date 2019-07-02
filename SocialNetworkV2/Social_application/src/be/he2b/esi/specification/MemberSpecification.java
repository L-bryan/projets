package be.he2b.esi.specification;

import core.Information;

/**
 * Class MemberSpecification
 * @author Bryan & Dylan
 */
public class MemberSpecification {
    
    private int id;


    public MemberSpecification(Information ...info){
        
    }

    public MemberSpecification(int id) {
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }    
}
