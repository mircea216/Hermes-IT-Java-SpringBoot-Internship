package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.RecruitmentDTO;


public interface RecruitmentServiceInterface {
    RecruitmentDTO getOne();

    void save(RecruitmentDTO recruitmentDTO);
}
