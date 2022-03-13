package ro.societateahermes.backendservice.utils.mapper;

import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Recruitment;
import ro.societateahermes.backendservice.entities.dto.RecruitmentDTO;

@Component
public class RecruitmentMapper {

    public RecruitmentDTO convertToDto(Recruitment recruitment) {
        if (recruitment.isStatus())
            return new RecruitmentDTO(recruitment.isStatus(), recruitment.getLink());

        RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
        recruitmentDTO.setStatus(recruitment.isStatus());
        return recruitmentDTO;

    }

    public Recruitment convertToRecruitment(RecruitmentDTO recruitmentDTO) {
        if (recruitmentDTO.isStatus())

            return new Recruitment(1L, recruitmentDTO.isStatus(), recruitmentDTO.getLink());
        Recruitment recruitment = new Recruitment();
        recruitment.setId(1L);
        recruitment.setStatus(recruitmentDTO.isStatus());
        return recruitment;
    }
}
