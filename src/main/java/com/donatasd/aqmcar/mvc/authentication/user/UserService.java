package com.donatasd.aqmcar.mvc.authentication.user;


import com.donatasd.aqmcar.mvc.authentication.role.RoleRepository;
import com.donatasd.aqmcar.mvc.authentication.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private SecurityService securityService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       SecurityService securityService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.securityService = securityService;
    }

    /**
     * Find user by his username
     *
     * @param username
     * @return existing username
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Create a new user by encoding his password using {@link BCryptPasswordEncoder#encode(CharSequence)} and setting
     * all existing roles.
     *
     * @param user
     */
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
    }

    public User findCurrentUser() {
        String username = securityService.getAuthenticationDetailsUsername();
        return userRepository.findByUsername(username);
    }

}
