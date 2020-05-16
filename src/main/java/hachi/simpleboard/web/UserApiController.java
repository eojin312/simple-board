package hachi.simpleboard.web;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.service.UserService;
import hachi.simpleboard.web.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController extends BaseApiController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @PostMapping("/users")
    public User create(@RequestBody UserCreateDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping("/users")
    public Page<User> getList(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable);
    }
}
