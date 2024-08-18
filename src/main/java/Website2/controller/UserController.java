package Website2.controller;
import Website2.model.entity.Users;
import Website2.model.request.UserRequest;
import Website2.service.Class.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Users>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Integer userId) {
        Optional<Users> optionalUsers = userService.getUserById(userId);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không có thông tin về id được tìm kiếm");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userService.CreateUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserRequest userRequest) {
        Optional<Users> optionalUsers = userService.getUserById(userId);
        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();
            if (user.getRole().name().equals("ADMIN")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không thể chỉnh sửa user có role là ADMIN");
            }
            userService.updateUser(userId, userRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER không có thông tin");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        Optional<Users> userToDelete = userService.getUserById(userId);
        if (userToDelete.isPresent()) {
            Users user = userToDelete.get();
            if (user.getRole().name().equals("ADMIN")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không thể xóa user có role là ADMIN");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER không có thông tin");
        }

        boolean delete = userService.deleteUser(userId);
        if (delete) {
            return ResponseEntity.ok("Xóa user thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Xóa không thành công");
        }
    }
}
