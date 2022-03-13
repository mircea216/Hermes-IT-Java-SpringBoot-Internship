package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.cdMember.CDMember;
import ro.societateahermes.backendservice.entities.cdMember.DepartmentRole;
import ro.societateahermes.backendservice.entities.dto.CDMemberDTO;
import ro.societateahermes.backendservice.repository.CDMemberRepositoryInterface;
import ro.societateahermes.backendservice.service.CDMemberServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.CDMemberMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CDMemberServiceImplementation implements CDMemberServiceInterface {
    private final CDMemberRepositoryInterface cdMemberRepository;


    CDMemberServiceImplementation(CDMemberRepositoryInterface cdMemberRepository) {
        this.cdMemberRepository = cdMemberRepository;
    }

    @Override
    public void save(CDMemberDTO CDMemberDto) {
        cdMemberRepository.save(CDMemberMapper.cdMemberDTOToCDMember(CDMemberDto));
    }

    @Override
    public List<CDMemberDTO> getAllCDMembers() {
        return cdMemberRepository.findAll().stream()
                .map(CDMemberMapper::cdMemberToCDMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long CDMemberID) {
        for (CDMember cdMember : cdMemberRepository.findAll())
            if (CDMemberID.equals(cdMember.getCDMemberID()))
                cdMemberRepository.delete(cdMember);
    }

    @Override
    public void update(CDMemberDTO cdMemberDto) {
        cdMemberRepository.save(CDMemberMapper.cdMemberDTOToCDMember(cdMemberDto));
    }

    public boolean isValid(CDMemberDTO cdMemberDTO) {
        if ((cdMemberDTO.getRoleType().equals(DepartmentRole.VICE_PRESIDENT) || cdMemberDTO.getRoleType().equals(DepartmentRole.MANAGER))
                && cdMemberDTO.getDepartmentId() == null) {
            return false;
        }

        return true;
    }
}
