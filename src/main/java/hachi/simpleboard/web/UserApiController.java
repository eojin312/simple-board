package hachi.simpleboard.web;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.service.UserService;
import hachi.simpleboard.web.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * userApiController
 */
@Slf4j
@RestController
@SessionAttributes("login")
@RequiredArgsConstructor
public class UserApiController extends BaseApiController {

    private final UserService userService;

    @ApiOperation(value = "회원 생성", notes = "id: long 타입")
    @PostMapping("/users")
    public Long create(@RequestBody @Valid UserDto.Create userDto) {
        return userService.save(userDto);
    }

    /**
     * user 검색 기능
     *
     * @param pageable
     * @param searchType
     * @param searchKeyword
     * @return
     */
    @GetMapping("/users")
    public Page<User> getList(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
        if (searchType != null && searchKeyword != null) {
            return userService.findAllBySearchCondition(pageable, searchType, searchKeyword);
        } else {
            return userService.findAll(pageable);
        }
    }

    @GetMapping("/users/{id}")
    public User detail(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지않는 회원입니다."));
    }

    @PutMapping("/users")
    public User update(@RequestBody UserDto.Update userUpdateDto) {
        return userService.update(userUpdateDto);
    }

    @DeleteMapping("/users/{id}")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
