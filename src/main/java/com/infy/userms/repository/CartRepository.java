package com.infy.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userms.entity.Cart;
import com.infy.userms.entity.CompositeKey;


@Repository
public interface CartRepository extends JpaRepository<Cart, CompositeKey> {

}
