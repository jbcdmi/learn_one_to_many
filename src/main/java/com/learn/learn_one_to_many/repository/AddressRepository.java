package com.learn.learn_one_to_many.repository;

import com.learn.learn_one_to_many.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}