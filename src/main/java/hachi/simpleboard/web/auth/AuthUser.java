package hachi.simpleboard.web.auth;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 회원 인증하는 클래스
 */
@Getter
@ToString
public class AuthUser extends User {

    private hachi.simpleboard.domain.user.User user;

    public AuthUser(hachi.simpleboard.domain.user.User _user) {
        super(_user.getUsername(), _user.getPassword(), List.of(new SimpleGrantedAuthority(_user.getRole())));
        this.user = _user;
    }
}
