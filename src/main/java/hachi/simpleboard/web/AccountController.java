package hachi.simpleboard.web;

import hachi.simpleboard.service.AccountService;
import hachi.simpleboard.web.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/join")
    public String joinPage() {
        return "/account/join";
    }

    @PostMapping("/api/account")
    public String join(@ModelAttribute AccountDto accountDto) {
        accountService.join(accountDto);
        return "redirect:/login";
    }
}
