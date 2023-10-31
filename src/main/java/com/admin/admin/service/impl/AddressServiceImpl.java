package com.admin.admin.service.impl;

import com.admin.admin.model.Address;
import com.admin.admin.repository.AddressRepository;
import com.admin.admin.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Autowired
    private final AddressRepository addressRepository;
    @Override
    public ResponseEntity<?> addAddress(Address address) {
        try {
            Address address1 = new Address(address.getUserid(), address.getAddress_shipping(), address.getName(),address.getEmail(),address.getPhone());
            addressRepository.save(address);
            return ResponseEntity.ok("200");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @Override
    public ResponseEntity<?> getAddressByUserId(String id) {
        try {
            addressRepository.deleteByUserid(id);
            return ResponseEntity.ok("200");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }

    }
    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        try {
            if (!addressRepository.findById(id).isPresent()){
                return ResponseEntity.ok("500");
            }
            addressRepository.deleteById(id);
            return ResponseEntity.ok("200");
        }catch (Exception e ) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
