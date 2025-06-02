package com.soufiane.authservice.services;

import com.soufiane.authservice.dtos.user.UserLoginRequest;
import com.soufiane.authservice.dtos.user.UserLoginResponse;
import com.soufiane.authservice.dtos.user.UserRegisterRequest;
import com.soufiane.authservice.dtos.user.UserRegisterResponse;
import com.soufiane.authservice.entities.User;
import com.soufiane.authservice.enums.Role;
import com.soufiane.authservice.mappers.UserMapper;
import com.soufiane.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    public UserRegisterResponse register(UserRegisterRequest user) {
        validateUserInputs(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
        checkIfUserExists(user.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));
        User userEntity=userMapper.registerRequestConvertToEntity(user);
        userEntity.setUuid(UUID.randomUUID());
        userEntity=userRepository.save(userEntity);
        UserRegisterResponse userRegisterResponse= userMapper.userConvertToResponse(userEntity);
        return userRegisterResponse;
    }


    private void checkIfUserExists(String email) {
        boolean emailExists = userRepository.findFirstByEmail(email) != null;
        if (emailExists) {
            throw new IllegalArgumentException("Un provider avec cet email existe déjà.");
        }
    }

    private void validateUserInputs(String firstName, String lastName, String email, String password, String role) {
        List<String> errors = new ArrayList<>();
        if (firstName == null || firstName.trim().isEmpty()) {
            errors.add("Le champ 'firstName' est vide");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            errors.add("Le champ 'lastName' est vide");
        }
        if (email == null || email.isEmpty()) {
            errors.add("Le champ 'email' est vide");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Le champ 'password' est vide");
        }
        boolean roleIsValid = false;
        for (Role r : Role.values()) {
            if (r.name().equals(role)) {
                roleIsValid = true;
                break;
            }
        }
        if (!roleIsValid) {
            errors.add("Rôle invalide : " + role);
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Erreur(s): " + String.join(", ", errors) + ".");
        }
    }

    public UserLoginResponse verify(UserLoginRequest user) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated()){
            User userEntity = userRepository.findFirstByEmail(user.getEmail());
            if (userEntity != null) {
                String role = userEntity.getRole().name(); // Assurez-vous de récupérer le rôle en tant que chaîne (par exemple "ROLE_ADMIN")
                String token = jwtService.generateToken(user.getEmail(), role);
                return new UserLoginResponse(token);
            }
        }
        throw new RuntimeException("Authentification échouée");
    }

}