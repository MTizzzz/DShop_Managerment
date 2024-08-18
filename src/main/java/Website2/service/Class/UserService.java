package Website2.service.Class;
import Website2.exception.AppException;
import Website2.exception.ErrorResponseBase;
import Website2.model.entity.Role;
import Website2.model.entity.Users;
import Website2.model.request.UserRequest;
import Website2.repository.UserRepository;
import Website2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optional = userRepository.findByUsername(username);
        if (optional.isPresent()) {
            Users user = optional.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
            return new User(user.getUsername(), user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException(username);
        }

    }


    @Override
    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> searchUsersByUsername(String username) {

        Optional<Users> optionalUsers = userRepository.findByUsername(username);
        return optionalUsers.map(Collections::singletonList).orElse(Collections.emptyList());

    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void CreateUser(@NotNull UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorResponseBase.DOUBLE_EMAIL_EX);
        }
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new AppException(ErrorResponseBase.DOUBLE_USERNAME_EX);
        }
        // tạo đối tượng để lưu

        Users users = new Users();
        users.setEmail(userRequest.getEmail());
        users.setPassword(encoder.encode(userRequest.getPassword()));
        users.setUsername(userRequest.getUsername());
        users.setPhone(userRequest.getPhone());
        users.setRole(Role.MANAGER);
        users.setFullName(userRequest.getFullName());
        userRepository.save(users);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Users updateUser(Integer userId, UserRequest userRequest) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorResponseBase.Id_not));
        users.setUsername(userRequest.getUsername());
        users.setPassword(encoder.encode(userRequest.getPassword()));
        users.setPhone(userRequest.getPhone());
        users.setFullName(userRequest.getFullName());
        users.setEmail(userRequest.getEmail());
        return userRepository.save(users);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteUser(Integer userId) {
        Optional<Users> optionalUsers = userRepository.findById(userId);
        if (optionalUsers.isPresent()) {
            userRepository.delete(optionalUsers.get());
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public Page<Users> search(SearchUsers searchUsers) {
//
//        Specification<Users> condition = UserSpecification.buildCondition(searchUsers);
//        PageRequest pageRequest = BaseRequest.buildPageRequest(searchUsers);
//
//        return userRepository.findAll(condition, pageRequest);
//    }


}
