package com.example.demo.Repo;

import com.example.demo.Entities.dbo.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM dbo.cart where (:username is null or username = :username) and status is null",
            countQuery = "SELECT count(*) FROM dbo.cart where (:username is null or username = :username) and status is null",
            nativeQuery = true)
    Page<Cart> get(@Param(value = "username") String username, Pageable pageable);
    @Query(value = "select * from cart where username = :username and status is null",
            nativeQuery = true)
    List<Cart> getAll(@Param(value = "username") String username);
    @Modifying
    @Query(value = "DELETE FROM dbo.cart where username = :username and product_id = :product_id and status is null",
            nativeQuery = true)
    void removeOne(@Param(value = "username") String username, @Param(value = "product_id") Integer productId);

    @Modifying
    @Query(value = "DELETE FROM dbo.cart where username = :username and status is null",
            nativeQuery = true)
    void removeAll(@Param(value = "username") String username);

    @Modifying
    @Query(value = "update dbo.cart set status = 1, date_pay = GETDATE() where username = :username and status is null",
            nativeQuery = true)
    void updateAllBought(@Param(value = "username") String username);
}