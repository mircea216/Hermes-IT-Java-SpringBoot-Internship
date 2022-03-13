package ro.societateahermes.backendservice.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.societateahermes.backendservice.entities.Admin;
import ro.societateahermes.backendservice.repository.AdminRepositoryInterface;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AdminRepositoryInterface adminRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return AdminDetailsImpl.build(admin);
	}

}
