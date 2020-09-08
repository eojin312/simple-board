package hachi.simpleboard.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Page<User> findAllByUsernameContaining(String searchKeyword, Pageable pageable);

    Page<User> findAllByNameContaining(String searchKeyword, Pageable pageable);

    Page<User> findAllByEmailContaining(String searchKeyword, Pageable pageable);
}