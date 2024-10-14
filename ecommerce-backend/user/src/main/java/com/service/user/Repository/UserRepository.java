package com.service.user.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.service.user.Model.User;
import java.util.Optional;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByCodeUser(String codeUser);
    Optional<User> findByEmail(String email);
    @SuppressWarnings("null")
    Page<User> findAll(Pageable pageable);
    void save(User user);
}
