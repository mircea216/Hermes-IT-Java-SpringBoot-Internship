package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.CDMemberDTO;

import java.util.List;

public interface CDMemberServiceInterface {
    void save(CDMemberDTO CDMemberDto);

    void delete(Long CDMemberID);

    void update(CDMemberDTO cdMemberDto);

    List<CDMemberDTO> getAllCDMembers();

    boolean isValid(CDMemberDTO cdMemberDTO);
}
