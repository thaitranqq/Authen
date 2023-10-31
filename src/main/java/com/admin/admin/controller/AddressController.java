package com.admin.admin.controller;

import com.admin.admin.model.Address;
import com.admin.admin.service.AddressService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private final AddressService addressService;
    @GetMapping("/getByUserID")
    public ResponseEntity<?> getByUserID(@RequestParam String id){
        return addressService.getAddressByUserId(id);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }
    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteByID(@RequestParam Long id){
        return addressService.deleteAddress(id);
    }
}
