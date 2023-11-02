package com.admin.admin.repository;

import com.admin.admin.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findById(Long id);
    Optional<Address> findByUserid(String id);
    List<Address> getAddressByUserid(String id);
}
