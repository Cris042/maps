package com.api.maps.doman.builder;

import com.api.maps.data.entity.Account;
import com.api.maps.doman.dto.AccountDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountBuilder {

    public Account fromEntity(AccountDTO accountDTO) {
        return new Account(
                accountDTO.getId() != null ? accountDTO.getId() : null,
                accountDTO.getBalance()
        );
    }

    public AccountDTO fromDTO(Account account) {
        return new AccountDTO(
                account.getId() != null ? account.getId() : null,
                account.getBalance()
        );
    }

}
