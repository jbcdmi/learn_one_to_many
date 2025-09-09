package com.learn.learn_one_to_many.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<com.learn.learn_one_to_many.entity.Customer, Long> {
}
