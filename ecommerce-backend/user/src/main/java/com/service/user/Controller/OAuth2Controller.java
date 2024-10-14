package com.service.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.Model.AuthResponse;
import com.service.user.Service.OAuth2Service;

@RestController
@RequestMapping("api/oauth2")
public class OAuth2Controller {

    @Autowired
    private OAuth2Service oAuth2Service;

    @GetMapping("/callback")
    public ResponseEntity<?> handleOAuth2Callback(OAuth2AuthenticationToken authenticationToken) {
        try {
            AuthResponse authResponse = oAuth2Service.processOAuth2User(authenticationToken);
            return ResponseEntity.ok(authResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
