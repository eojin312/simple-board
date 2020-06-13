package hachi.simpleboard.service;

import hachi.simpleboard.domain.account.Account;
import hachi.simpleboard.domain.account.AccountRepository;
import hachi.simpleboard.web.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long join(AccountDto accountDto) {
        Account account = accountDto.toEntity(passwordEncoder);
        accountRepository.save(account);
        return account.getId();
    }
}
