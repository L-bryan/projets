package be.he2b.esi.dto;

import be.he2b.esi.exception.DtoException;
import core.BirthDateInformation;
import core.ContactInformation;
import core.Information;
import core.InformationType;
import core.NameInformation;
import core.PreferenceInformation;
import core.SexInformation;
import java.util.ArrayList;
import java.util.List;

/**
 * Class MemberDto
 *
 * @author Dylan et Bryan
 */
public class MemberDto extends EntityDto<Integer> {

    private List<Information> informations;

    public MemberDto(Integer id, Information... info) throws DtoException {
        this(info);
        this.id = id;
    }

    public MemberDto(Information... information) throws DtoException{
        this.informations=new ArrayList();
        for (Information info : information) {
            this.informations.add(info);
        }

    }

    private Information getInfo(InformationType type) {
        for (Information info : informations) {
            if (info.getType() == type) {
                return info;
            }
        }
        return null;
    }

    public NameInformation getNameInformation() {
        return (NameInformation) getInfo(InformationType.NAME);
    }

    public ContactInformation getContactInformation() {
        return (ContactInformation) getInfo(InformationType.CONTACT);
    }

    public PreferenceInformation getPreferenceInformation() {
        return (PreferenceInformation) getInfo(InformationType.PREFERENCE);
    }

    public BirthDateInformation getBirthDateInformation() {
        return (BirthDateInformation) getInfo(InformationType.BRITHDATE);
    }

    public SexInformation getSexInformation() {
        return (SexInformation) getInfo(InformationType.SEX);
    }

}
