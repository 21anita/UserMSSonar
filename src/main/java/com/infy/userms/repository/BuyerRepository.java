package com.infy.userms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userms.entity.Buyer;


@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
	Optional<Buyer> findById(Integer buyerid);
	Optional<Buyer> findByEmail(String email);
	Optional<Buyer> findByPhonenumber(String phonenumber);

}
