package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.societateahermes.backendservice.entities.Admin;
import ro.societateahermes.backendservice.entities.BoardRole;
import ro.societateahermes.backendservice.entities.Role;
import ro.societateahermes.backendservice.payload.request.LoginRequest;
import ro.societateahermes.backendservice.payload.request.SignupRequest;
import ro.societateahermes.backendservice.payload.response.JwtResponse;
import ro.societateahermes.backendservice.payload.response.MessageResponse;
import ro.societateahermes.backendservice.repository.AdminRepositoryInterface;
import ro.societateahermes.backendservice.repository.RoleRepositoryInterface;
import ro.societateahermes.backendservice.security.jwt.JwtUtils;
import ro.societateahermes.backendservice.security.services.AdminDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AdminRepositoryInterface adminRepository;

    @Autowired
    RoleRepositoryInterface roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_CARIERE = "cariereIT";
    public static final String ROLE_GUIDE_DAYS = "guideDays";
    public static final String ROLE_HACKATHON = "hackathon";
    public static final String ROLE_DESTRESIUNE = "destresiune";


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AdminDetailsImpl adminDetails = (AdminDetailsImpl) authentication.getPrincipal();
        List<String> roles = adminDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(adminDetails.getId());
        jwtResponse.setEmail(adminDetails.getEmail());
        jwtResponse.setUsername(adminDetails.getUsername());
        jwtResponse.setRoles(roles);
        jwtResponse.setToken(jwt);

        return ResponseEntity.ok(jwtResponse);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (adminRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (adminRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new admin's account
        Admin admin = new Admin();
        admin.setUsername(signUpRequest.getUsername());
        admin.setEmail(signUpRequest.getEmail());
        admin.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(BoardRole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case ROLE_ADMIN:
                        Role adminRole = roleRepository.findByName(BoardRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case ROLE_CARIERE:
                        Role cariereRole = roleRepository.findByName(BoardRole.PROJECT_MANAGER_CARIEREIT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(cariereRole);

                        break;
                    case ROLE_GUIDE_DAYS:
                        Role guideRole = roleRepository.findByName(BoardRole.PROJECT_MANAGER_GUIDEDAYS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(guideRole);

                        break;
                    case ROLE_HACKATHON:
                        Role hackathonRole = roleRepository.findByName(BoardRole.PROJECT_MANAGER_HACKATHON)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(hackathonRole);

                        break;
                    case ROLE_DESTRESIUNE:
                        Role destresiuneRole = roleRepository.findByName(BoardRole.PROJECT_MANAGER_DESTRESIUNE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(destresiuneRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(BoardRole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        admin.setRoles(roles);
        adminRepository.save(admin);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
