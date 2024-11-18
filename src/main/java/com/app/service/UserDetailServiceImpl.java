package com.app.service;

import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " +  username + " no existe."));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name())));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(),userEntity.getEnabled(),
                userEntity.getAccountNoExpired(), userEntity.getCredentialsNoExpired(), userEntity.getAccountNoLocked(),
                authorities);
    }
}
