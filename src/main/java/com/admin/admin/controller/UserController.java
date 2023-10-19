package com.admin.admin.controller;

import com.admin.admin.model.UsersRequest;
import com.admin.admin.repository.UserCusRepo;
import com.admin.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserCusRepo userCusRepo;
    @GetMapping("/admin/getAll")
    public List<UsersRequest> getAll(){
        return userCusRepo.findAllUsers();
    }

    @GetMapping("/profile")
    public List<UsersRequest> getAll(@RequestParam String id){
        return userCusRepo.findUserById(id) ;
    }
}
