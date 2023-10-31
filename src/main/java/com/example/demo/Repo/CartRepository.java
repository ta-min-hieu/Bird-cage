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
    @Query(value = "SELECT * FROM dbo.cart where (:username is null or username = :username) " +
            "AND ((:status IS NULL AND status IS NULL) OR (status = :status)) ",
            countQuery = "SELECT count(*) FROM dbo.cart where (:username is null or username = :username) " +
                    "AND ((:status IS NULL AND status IS NULL) OR (status = :status)) ",
            nativeQuery = true)
    Page<Cart> get(@Param(value = "username") String username,
                   @Param(value = "status") Integer status,
                   Pageable pageable);
    @Query(value = "select * from cart where username = :username and (:status is null or status = :status) ",
            nativeQuery = true)
    List<Cart> getAll(@Param(value = "username") String username, @Param(value = "status") Integer status);
    @Modifying
    @Query(value = "DELETE FROM dbo.cart where username = :username and product_id = :product_id and status is null",
            nativeQuery = true)
    void removeOne(@Param(value = "username") String username, @Param(value = "product_id") Integer productId);

    @Modifying
    @Query(value = "DELETE FROM dbo.cart where username = :username and status is null",
            nativeQuery = true)
    void removeAll(@Param(value = "username") String username);

    @Modifying
    @Query(value = "update dbo.cart set status = 1, date_pay = GETDATE(), bill_id = :bill_id, created_date = :createdDate," +
            "expected_date = :expectedDate " +
            "where username = :username and ((:status IS NULL AND status IS NULL) OR (status = :status))",
            nativeQuery = true)
    void updateAllBought(@Param(value = "username") String username,
                         @Param(value = "status") Integer status,
                         @Param(value = "bill_id") Integer billId,
                         @Param(value = "createdDate") String createdDate,
                         @Param(value = "expectedDate") String expectedDate);

    @Query(value = "select * from cart where status is not null and status != 2", nativeQuery = true)
    List<Cart> getAllBill();
    @Query(value = "select * from cart where (:product_id IS NULL AND product_id IS NOT NULL) OR (:product_id IS NOT NULL AND product_id IS NULL)",
            countQuery = "SELECT count(*) FROM cart where (:product_id IS NULL AND product_id IS NOT NULL) OR (:product_id IS NOT NULL AND product_id IS NULL)",
            nativeQuery = true)
    Page<Cart> getAllBill(@Param(value = "product_id") Integer product_id, Pageable pageable);
    @Query(value = "select sum(cage_price) from dbo.cart inner join production.regular_cages on cart.product_id = regular_cages.cage_id\n" +
            "where cart.status is not null", nativeQuery = true)
    String sumPriceBillAll();

    @Query(value = "select sum(cage_price) from dbo.cart inner join production.regular_cages on cart.product_id = regular_cages.cage_id\n" +
            "where cart.status is null and username = :username", nativeQuery = true)
    String sumPriceById(@Param(value = "username") String username);

    @Modifying
    @Query(value = "DELETE FROM dbo.cart where username = :username and status = 2", nativeQuery = true)
    void removeAllOrderCustomize(@Param(value = "username") String username);

    @Query(value = "select TOP 1 bill_id from dbo.cart order by bill_id desc", nativeQuery = true)
    Integer countBillId();

    @Query(value = "select * from cart where bill_id = :bill_id", nativeQuery = true)
    List<Cart> getAllByBillId(@Param(value = "bill_id") Integer billId);
    @Query(value = "select * from cart where bill_id = :bill_id",
            countQuery = "SELECT count(*) FROM cart where bill_id = :bill_id",
            nativeQuery = true)
    Page<Cart> getAllByBillId(@Param(value = "bill_id") Integer billId, Pageable pageable);
    @Query(value = "select * from cart where bill_id = :bill_id",
            countQuery = "select count(*) from cart where bill_id = :bill_id", nativeQuery = true)
    Page<Cart> getPageByBillId(@Param(value = "bill_id") Integer billId, Pageable pageable);

    @Modifying
    @Query(value = "update dbo.cart set status = :status where bill_id = :bill_id", nativeQuery = true)
    void updateStatusByBillId(@Param(value = "status") Integer status,
                                 @Param(value = "bill_id") Integer billId);
}