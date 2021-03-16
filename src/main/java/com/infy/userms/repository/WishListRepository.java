package com.infy.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.userms.entity.CompositeKey;
import com.infy.userms.entity.WishList;


@Repository
public interface WishListRepository extends JpaRepository<WishList, CompositeKey>{

}
