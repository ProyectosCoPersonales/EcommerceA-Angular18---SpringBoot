package com.service.user.Service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import com.service.user.Model.AuthResponse;

public interface OAuth2Service {
    AuthResponse processOAuth2User(OAuth2AuthenticationToken authenticationToken);
}
