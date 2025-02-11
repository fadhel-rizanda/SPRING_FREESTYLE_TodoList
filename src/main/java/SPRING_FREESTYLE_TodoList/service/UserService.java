package SPRING_FREESTYLE_TodoList.service;

import SPRING_FREESTYLE_TodoList.dto.UserDTO;
import SPRING_FREESTYLE_TodoList.dto.UserRegisterDTO;
import SPRING_FREESTYLE_TodoList.model.Users;
import SPRING_FREESTYLE_TodoList.service.security.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import SPRING_FREESTYLE_TodoList.repository.TodoListRepository;
import SPRING_FREESTYLE_TodoList.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoListRepository todoListRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Page<UserDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = userRepository.findAll(pageable);
        return users.map(UserMapper::toUserDTO);
    }

    public Optional<UserDTO> findById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return Optional.of(UserMapper.toUserDTO(user));
    }

    public Page<UserDTO> findByUsername(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> user = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
        return user.map(UserMapper::toUserDTO);
    }

    public UserRegisterDTO createUser(UserRegisterDTO userDTO) {
        log.warn("Creating user: {}", userDTO);
        Users user = UserMapper.toUserEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.password()));
        userRepository.save(user);
        return UserMapper.toUserRegisterDTO(user);
    }

    public UserDTO createOauthUser(OAuth2User oauth, String oauthProvider) {
        log.warn("Creating user: {}", oauth);
        Users user = new Users();
        user.setUsername(oauth.getName());
        user.setPassword(encoder.encode(UUID.randomUUID().toString()));
        user.setEmail(oauth.getAttribute("email"));
        user.setOauthProvider(oauthProvider);
        userRepository.save(user);

        return UserMapper.toUserDTO(user);
    }

    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        Users user = userRepository.findById(userDTO.id()).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setTodoLists(userDTO.todoLists());

        // Cek apakah password yang diberikan berbeda dari yang ada di database
        if (userDTO.password() != null && !encoder.matches(userDTO.password(), user.getPassword())) {
            // Enkripsi password jika berbeda
            user.setPassword(encoder.encode(userDTO.password()));
        }

        userRepository.save(user);
        return Optional.of(UserMapper.toUserDTO(user));
    }

    public Boolean deleteUser(Long id, String password) {
        Users user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        // Validasi password sebelum menghapus
        if (encoder.matches(password, user.getPassword())) {
            userRepository.deleteById(user.getId());
            return true;
        }
        return false;
    }

//    6
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;
    public String verify(UserDTO UserDTO){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(UserDTO.username(), UserDTO.password()));
        if(authentication.isAuthenticated()) return jwtService.generateToken(UserDTO.username());
        return "Fail";
    }
}

