package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.repository.AnswerRepositoryInterface;
import ro.societateahermes.backendservice.service.AnswerServiceInterface;

@Service
public class AnswerServiceImplementation implements AnswerServiceInterface {


    @Autowired
    private  AnswerRepositoryInterface answerRepository;

}
