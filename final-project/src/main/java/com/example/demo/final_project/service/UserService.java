package com.example.demo.final_project.service;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.final_project.model.Role;
import com.example.demo.final_project.model.User;
import com.example.demo.final_project.repository.RoleRepository;
import com.example.demo.final_project.repository.UserRepository;
  @Service
public class UserService {
  
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String rawPassword, Set<String> rolesName) {
        System.out.println("Cercando il ruolo: " + rolesName);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); // QUI LA CODIFICA!

        Set<Role> roles = new HashSet<>();
        for (String roleName : rolesName) {
            Role role = roleRepository.findByName(roleName)
                          .orElseThrow(() -> new RuntimeException("Ruolo non trovato: " + roleName));
            roles.add(role);
        }

        user.setRoles(roles);

        return userRepository.save(user);
    }
}


