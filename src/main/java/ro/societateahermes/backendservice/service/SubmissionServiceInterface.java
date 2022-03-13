package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.MySubmissionDTO;

public interface SubmissionServiceInterface {

    void savefromDTO(MySubmissionDTO submission);
}
