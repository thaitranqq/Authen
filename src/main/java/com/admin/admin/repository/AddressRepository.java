package com.admin.admin.repository;

import com.admin.admin.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findById(Long id);
    Optional<Address> findByUserid(String id);
    void deleteByUserid(String id);
}
