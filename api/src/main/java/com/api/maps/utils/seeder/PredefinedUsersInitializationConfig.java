package com.api.maps.utils.seeder;

import com.api.maps.data.repository.IFinancialActiveRepository;
import com.api.maps.data.repository.IUserRepository;
import com.api.maps.doman.service.auth.IAuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
@Slf4j
public class PredefinedUsersInitializationConfig {

    private final IAuthService authService;

    @PostConstruct
    public void initializePredefinedUsers() {
        log.info("Iniciando criação de usuários pré-definidos...");
        authService.createPredefinedUsers();
    }
}
