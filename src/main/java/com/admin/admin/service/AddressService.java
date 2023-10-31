package com.admin.admin.service;

import com.admin.admin.model.Address;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<?> addAddress(Address address);
    ResponseEntity<?> getAddressByUserId(String id);
    ResponseEntity<?> deleteAddress(Long id);
}
