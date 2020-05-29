package hachi.simpleboard.web;

import hachi.simpleboard.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AjaxResponseBody {

    List<User> result;
}
