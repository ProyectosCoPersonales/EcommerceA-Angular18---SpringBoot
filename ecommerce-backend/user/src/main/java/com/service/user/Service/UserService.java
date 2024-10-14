package com.service.user.Service;

import org.springframework.data.domain.Page;
import com.service.user.Model.User;
import com.service.user.Model.UserPasswordChangeDTO;

public interface UserService {
    User getUserByCodeUser(String codeUser);
    boolean updatePassword(UserPasswordChangeDTO passwordChangeDTO);
    Page<User> getAllUsers(int page, int size);
    User findByEmail(String email);
}