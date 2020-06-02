package hachi.simpleboard.service;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import hachi.simpleboard.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private List<User> users;

//    private final ModelMapper modelMapper;

    @Transactional
    public Long save(UserDto.Create userDto) {
        return userRepository.save(userDto.toEntity()).getId();
    }

    public Page<User> findAll(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10); // getter, setter 가 없어서 PageRequest.of 로 새로운 객체 생성
        return userRepository.findAll(pageable);
    }

    public Optional<User> findByid(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User update(UserDto.Update userUpdateDto) {
        return userRepository.save(userUpdateDto.toEntity());
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다."));
        userRepository.delete(user);
    }

    public Page<User> findAllBySearchCondition(Pageable pageable, String searchType, String searchKeyword) {
        if (searchType.equals("loginId")) {
            return userRepository.findByLoginIdContaining(searchKeyword, pageable);
        } else if (searchType.equals("name")) {
            return userRepository.findByNameContaining(searchKeyword, pageable);
        } else if (searchType.equals("email")) {
            return userRepository.findByEmailContaining(searchKeyword, pageable);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "알 수 없는 검색 타입입니다.");
        }
    }
}
