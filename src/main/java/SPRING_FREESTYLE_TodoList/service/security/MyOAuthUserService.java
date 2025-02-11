package SPRING_FREESTYLE_TodoList.service.security;

import SPRING_FREESTYLE_TodoList.dto.UserDTO;
import SPRING_FREESTYLE_TodoList.model.Users;
import SPRING_FREESTYLE_TodoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyOAuthUserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.error("\n\nLOAD  OAUTH {}", userRequest," \n\n");
        // Verifikasi data pengguna yang diterima dari OAuth
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        log.error("OAuth2 User Email: {}", email);

        // Lakukan operasi penyimpanan pengguna ke database
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User not found, creating new user.");
            user = new Users();
            user.setUsername(oAuth2User.getAttribute("name"));
            user.setEmail(email);
            user.setOauthProvider(userRequest.getClientRegistration().getRegistrationId());
            user.setPassword(UUID.randomUUID().toString()); // Generate random password
            userRepository.save(user);
        }

        return oAuth2User;
    }
}

