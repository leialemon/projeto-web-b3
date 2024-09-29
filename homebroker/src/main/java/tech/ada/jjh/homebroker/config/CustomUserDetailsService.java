package tech.ada.jjh.homebroker.config;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.AccessUser;
import tech.ada.jjh.homebroker.model.Role;
import tech.ada.jjh.homebroker.repository.AccessUsersRepository;

import java.util.*;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccessUsersRepository accessUsersRepository;

    public CustomUserDetailsService(AccessUsersRepository accessUsersRepository){
        this.accessUsersRepository = accessUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccessUser user = accessUsersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
