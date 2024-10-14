package com.service.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.user.Model.User;
import com.service.user.Model.UserPasswordChangeDTO;
import com.service.user.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{codeUser}")
    public ResponseEntity<User> getUserByCodeUser(@PathVariable String codeUser) {
        User user = userService.getUserByCodeUser(codeUser);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/page")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam int page, @RequestParam int size) {
        Page<User> PageUser = userService.getAllUsers(page, size);
        return ResponseEntity.ok(PageUser);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UserPasswordChangeDTO passwordChangeDTO) {
        boolean updated = userService.updatePassword(passwordChangeDTO);
        return updated ? ResponseEntity.ok("Password updated successfully")
                : ResponseEntity.badRequest().body("Error updating password");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean IsAdmin() {
        return true;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public boolean IsClient() {
        return true;
    }
}
