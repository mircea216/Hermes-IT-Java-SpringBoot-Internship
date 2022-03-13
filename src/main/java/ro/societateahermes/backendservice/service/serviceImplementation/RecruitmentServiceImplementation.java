package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.dto.RecruitmentDTO;
import ro.societateahermes.backendservice.repository.RecruitmentRepositoryInterface;
import ro.societateahermes.backendservice.service.RecruitmentServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.RecruitmentMapper;


@Service
public class RecruitmentServiceImplementation implements RecruitmentServiceInterface {

    @Autowired
    private RecruitmentRepositoryInterface recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;


    @Override
    public RecruitmentDTO getOne() {
        return recruitmentMapper.convertToDto(recruitmentRepository.findById(1L).orElseThrow());
    }

    @Override
    public void save(RecruitmentDTO recruitmentDTO) {
        recruitmentRepository.save(recruitmentMapper.convertToRecruitment(recruitmentDTO));

    }
}
