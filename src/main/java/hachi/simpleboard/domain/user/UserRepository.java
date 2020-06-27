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

    Page<User> findByUsernameContaining(String searchKeyword, Pageable pageable);

    Page<User> findByNameContaining(String searchKeyword, Pageable pageable);

    Page<User> findByEmailContaining(String searchKeyword, Pageable pageable);
}