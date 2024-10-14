package com.service.user.Service;

import org.springframework.stereotype.Service;

import com.service.user.Model.User;
import com.service.user.Model.UserPasswordChangeDTO;
import com.service.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByCodeUser(String codeUser) {
        return userRepository.findByCodeUser(codeUser).orElse(null);
    }

    public boolean updatePassword(UserPasswordChangeDTO passwordChangeDTO) {
        User user = userRepository.findByEmail(passwordChangeDTO.getEmail()).get();
        if (user != null) {
            if (passwordEncoder.matches(passwordChangeDTO.getOldPassword(), user.getPassword())) {
                if (!passwordEncoder.matches(passwordChangeDTO.getNewPassword(), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
                    userRepository.save(user);
                    return true;
                }
            }
        }
        return false; 
    }

    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

}
