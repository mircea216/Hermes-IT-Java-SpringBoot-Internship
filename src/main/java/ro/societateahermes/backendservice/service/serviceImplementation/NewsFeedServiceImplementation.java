package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.dto.NewsFeedDTO;
import ro.societateahermes.backendservice.repository.NewsFeedRepositoryInterface;
import ro.societateahermes.backendservice.service.NewsFeedServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.NewsFeedMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsFeedServiceImplementation implements NewsFeedServiceInterface {

    private final NewsFeedRepositoryInterface newsFeedRepository;
    private final NewsFeedMapper newsFeedMapper;

    public NewsFeedServiceImplementation(NewsFeedRepositoryInterface newsFeedRepository,NewsFeedMapper newsFeedMapper){
        this.newsFeedRepository = newsFeedRepository;
        this.newsFeedMapper = newsFeedMapper;
    }

    public List<NewsFeedDTO> getAllPost(){
        return newsFeedRepository.findAll().stream().map(newsFeedMapper::convertToDTO).collect(Collectors.toList());
    }

    public void createPost(NewsFeedDTO newsFeedDTO){
        newsFeedRepository.save(newsFeedMapper.convertToEntity(newsFeedDTO));
    }
    public void deletePost(Long idPost){
        newsFeedRepository.delete(newsFeedRepository.findById(idPost).orElseThrow());
    }

}
