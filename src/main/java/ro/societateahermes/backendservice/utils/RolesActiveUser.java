package ro.societateahermes.backendservice.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import ro.societateahermes.backendservice.security.services.AdminDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RolesActiveUser {
    public static List<String> getRoles() {
        AdminDetailsImpl adminDetails = (AdminDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = adminDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return roles;
    }
}
