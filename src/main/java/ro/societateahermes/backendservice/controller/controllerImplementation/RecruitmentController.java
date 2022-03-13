package ro.societateahermes.backendservice.controller.controllerImplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.societateahermes.backendservice.entities.dto.RecruitmentDTO;
import ro.societateahermes.backendservice.entities.dto.SponsorDTO;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.RecruitmentServiceInterface;
import ro.societateahermes.backendservice.service.SponsorServiceInterface;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;


import java.util.List;

@RestController
@RequestMapping("/recruitment")
public class RecruitmentController {

    @Autowired
    private RecruitmentServiceInterface recruitmentService;

    @GetMapping
    public RecruitmentDTO getOne() {
        return recruitmentService.getOne();
    }

    @PostMapping()
    public void save(@RequestBody RecruitmentDTO recruitmentDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.checkAdmin(roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        recruitmentService.save(recruitmentDTO);
    }
}
