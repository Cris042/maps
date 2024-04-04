package com.api.maps.doman.service.auth;

import com.api.maps.data.entity.Account;
import com.api.maps.data.entity.Role;
import com.api.maps.data.entity.User;
import com.api.maps.data.repository.IAccountRepository;
import com.api.maps.data.repository.IFinancialActiveRepository;
import com.api.maps.data.repository.IRoleRepository;
import com.api.maps.data.repository.IUserRepository;
import com.api.maps.doman.builder.AccountBuilder;
import com.api.maps.doman.builder.UserBuilder;
import com.api.maps.doman.dto.CreateUserDTO;
import com.api.maps.doman.dto.UserInfoResponseDTO;
import com.api.maps.doman.enums.RoleEnum;
import com.api.maps.utils.jwt.JwtUtils;
import com.api.maps.utils.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final UserBuilder userBuilder;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final IAccountRepository accountRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public synchronized Boolean existsByUsername(String username) {
         return userRepository.existsByUsername(username);
    }



    @Override
    public synchronized ResponseEntity<?> signin(CreateUserDTO createUserDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(createUserDTO.getUsername(), createUserDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(  new UserInfoResponseDTO( userDetails.getUsername(), userDetails.getAuthorities() ) );
    }

    @Override
    @Transactional
    public synchronized void signup(CreateUserDTO createUserDTO) {

        if (userRepository.existsByUsername(createUserDTO.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        Account account = new Account(null, 0.0);
        accountRepository.save(account);

        createUserDTO.setPassword(encoder.encode(createUserDTO.getPassword()));

        Role role = roleRepository.findByName(RoleEnum.ROLE_USER.toString())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        userRepository.save( userBuilder.fromAuthEntity( createUserDTO,role,account ) );
    }

    @Override
    @Transactional
    public synchronized void createPredefinedUsers() {

        if (!userRepository.existsByUsername("root")) {
            CreateUserDTO rootUserDTO = new CreateUserDTO();
            rootUserDTO.setUsername("root");
            rootUserDTO.setPassword(encoder.encode("spiderman"));

            Role role = roleRepository.findByName(RoleEnum.ROLE_ADM.toString())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            Account account = new Account(null, 0.0);
            accountRepository.save(account);

            userRepository.save( userBuilder.fromAuthEntity( rootUserDTO,role,account ) );

            for (int i = 0; i < 10; i++) {
                String username = "usuario" + i;
                String password = "senha" + i;

                CreateUserDTO userDTO = new CreateUserDTO();
                userDTO.setUsername(username);
                userDTO.setPassword(password);
                signup(userDTO);

            }

        }

    }

}
