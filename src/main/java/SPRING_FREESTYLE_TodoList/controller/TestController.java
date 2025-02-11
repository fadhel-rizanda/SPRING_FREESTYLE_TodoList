package SPRING_FREESTYLE_TodoList.controller;

import SPRING_FREESTYLE_TodoList.service.UserService;
import SPRING_FREESTYLE_TodoList.service.security.MyOAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private MyOAuthUserService myOAuthUserService;

    @GetMapping
    public String test() {
        return "test";
    }

//    @GetMapping("/oauth")
//    public String testOauth(@AuthenticationPrincipal OAuth2UserRequest oauth) {
//
////        String name = oauth.getName();
////        String email = oauth.getAttribute("email");
//
////        userService.createOauthUser(oauth, "google");
//        myOAuthUserService.loadUser(oauth);
//
//        return "test oauth";
//        // proses validasi semau berjalan di server provider jadi yang bakal
//        // divalidasi sma kita adalah header dan signature tapi payload kg jd
//        // kalaupun dari oauth kaga ngasih data user seengganya bisa pake data
//        // kaya username sm emailnya/
//        // tinggal lanjutin jwt oauth secara konsep sama kaya bikin jwt biasa
//        // cmn bedanya kaga ada password
//
//    }
    @GetMapping("/oauth")
    public String testOauth(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // Ambil data pengguna yang login via OAuth
        String username = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

        // Tampilkan data pengguna untuk keperluan debugging
        return "Authenticated via OAuth. Username: " + username + ", Email: " + email;
    }
}
