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

    // spring security 에 user 도 있어서 헷갈리지않게 구분했다
    private hachi.simpleboard.domain.user.User user;

    public AuthUser(hachi.simpleboard.domain.user.User _user) {
        super(_user.getUsername(), _user.getPassword(), List.of(new SimpleGrantedAuthority(_user.getRole())));
        this.user = _user;
    }
}
