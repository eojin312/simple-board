package hachi.simpleboard.service;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import hachi.simpleboard.web.dto.UserCreateDto;
import hachi.simpleboard.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    private final ModelMapper modelMapper;

    @Transactional
    public Long save(UserCreateDto userDto) {
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

    public User update(UserUpdateDto userUpdateDto) {
        return userRepository.save(userUpdateDto.toEntity());
    }
}
