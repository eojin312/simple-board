package hachi.simpleboard.web;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.service.UserService;
import hachi.simpleboard.web.dto.UserCreateDto;
import hachi.simpleboard.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController extends BaseApiController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public Long create(@RequestBody UserCreateDto userDto) {

        return userService.save(userDto);
    }

    @GetMapping("/users")
    public Page<User> getList(@PageableDefault Pageable pageable) {
        Page<User> userList = userService.findAll(pageable);
        return userList;
    }

    @GetMapping("/users/{id}")
    public User detail(@PathVariable Long id) {
        return userService.findByid(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지않는 회원입니다."));
    }

    @PutMapping("/users")
    public User update(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(userUpdateDto);
    }

    @DeleteMapping("/users/{id}")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
