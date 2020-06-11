package hachi.simpleboard.web;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.service.UserService;
import hachi.simpleboard.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Slf4j
@RestController
@SessionAttributes("login")
@RequiredArgsConstructor
public class UserApiController extends BaseApiController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @PostMapping("/users")
    public Long create(@RequestBody @Valid UserDto.Create userDto) {
        return userService.save(userDto);
    }

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
        return userService.findByid(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지않는 회원입니다."));
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

    @RequestMapping(value = "/login")
    public ModelAndView loginCheck(@ModelAttribute User user, HttpSession session) {
        boolean result = userService.loginCheck(user, session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        if (result) {
            mav.addObject("msg", "로그인 성공");
        } else {
            mav.addObject("msg", "로그인 실패");
        }
        return mav;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        userService.logout(session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("msg", "logout");

        return mav;
    }
}
