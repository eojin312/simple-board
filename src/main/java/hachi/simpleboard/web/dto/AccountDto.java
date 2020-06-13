package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
public class AccountDto {

    private String username;
    private String password;

    public Account toEntity(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role("MEMBER")
                .build();
    }
}
