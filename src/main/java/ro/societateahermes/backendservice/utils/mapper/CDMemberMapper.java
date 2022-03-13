package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.cdMember.CDMember;
import ro.societateahermes.backendservice.entities.dto.CDMemberDTO;

public class CDMemberMapper {
    public static CDMemberDTO cdMemberToCDMemberDTO(CDMember cdMember) {
        if (cdMember == null) return null;
        CDMemberDTO cdMemberDTO = new CDMemberDTO();
        cdMemberDTO.setId(cdMember.getCDMemberID());
        cdMemberDTO.setImagePath(cdMember.getImagePath());
        cdMemberDTO.setFacebookLink(cdMember.getFacebookLink());
        cdMemberDTO.setName(cdMember.getName());
        cdMemberDTO.setDescription(cdMember.getDescription());
        cdMemberDTO.setDepartmentId(cdMember.getDepartmentId());
        cdMemberDTO.setRoleId(cdMember.getRoleId());
        return cdMemberDTO;
    }

    public static CDMember cdMemberDTOToCDMember(CDMemberDTO cdMemberDTO) {
        if (cdMemberDTO == null) return null;
        CDMember cdMember = new CDMember();
        cdMember.setCDMemberID(cdMemberDTO.getId());
        cdMember.setImagePath(cdMemberDTO.getImagePath());
        cdMember.setFacebookLink(cdMemberDTO.getFacebookLink());
        cdMember.setName(cdMemberDTO.getName());
        cdMember.setDescription(cdMemberDTO.getDescription());
        cdMember.setDepartmentId(cdMemberDTO.getDepartmentId());
        cdMember.setRoleId(cdMemberDTO.getRoleId());
        return cdMember;
    }
}
