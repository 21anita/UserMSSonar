package com.infy.userms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userms.entity.Seller;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{
	Optional<Seller> findById(Integer sellerId);
	Optional<Seller> findByPhonenumber(String phonenumber);
	Optional<Seller> findByEmail(String email);

}
