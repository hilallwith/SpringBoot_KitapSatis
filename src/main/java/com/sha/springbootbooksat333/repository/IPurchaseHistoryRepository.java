package com.sha.springbootbooksat333.repository;

import com.sha.springbootbooksat333.model.PurchaseHistory;

import com.sha.springbootbooksat333.repository.projection.IPurchaseItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPurchaseHistoryRepository extends CrudRepository<PurchaseHistory,Long> {

    @Query("select " +
            "b.title as title, ph.price as price, ph.purchaseTime as purchaseTime  " +  //purchaseItem kısmındaki lanları al
            "from PurchaseHistory ph left join Book b on b.id = ph.bookId " +         //  gelen id kitap id ye eşitse
            "where ph.userId = :userId")                                                   //userId ye göre yap
    List<IPurchaseItem> findAllPurchasesOfUser(@Param("userId")Long userId);
    //Satın alma öğelerinin listesini göndericez    //ve userId bekliycez




}
