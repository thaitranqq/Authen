package com.admin.admin.controller;

import com.admin.admin.model.Users;
import com.admin.admin.model.UsersRequest;
import com.admin.admin.repository.UserCusRepo;
import com.admin.admin.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserCusRepo userCusRepo;
    @Value("${secret.key")
    private String secretkey;
    @GetMapping("/admin/getAll")
    public List<UsersRequest> getAll(){
        return userCusRepo.findAllUsers();
    }
    @GetMapping("/count")
    public int countUser(){
        return (int) userRepository.count();
    }
    @GetMapping("/profile")
    public List<UsersRequest> getAll(@RequestParam String id){
        return userCusRepo.findUserById(id) ;
    }
    @PostMapping("/change-profile")
    public ResponseEntity<?> changeProfile(@RequestParam String token,
                                        @RequestParam String username,
                                        @RequestParam String oldPassword,
                                        @RequestParam String password){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(secretkey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String id = claims.getSubject();
            Optional<Users> users1 = userRepository.findByEmail(id);
            if(!users1.isPresent()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Faild");
            }
            users1.stream().forEach(users2 -> {
                if(!username.equals("")){
                    users2.setUsername(username);
                }
                if(passwordEncoder.matches(oldPassword,users2.getPassword())){
                    users2.setPassword(passwordEncoder.encode(password));
                }
                userRepository.save(users2);
            });
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Succes");
    }
}
