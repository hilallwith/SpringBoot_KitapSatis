package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.PurchaseHistory;
import com.sha.springbootbooksat333.repository.IPurchaseHistoryRepository;
import com.sha.springbootbooksat333.repository.projection.IPurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseHistoryService implements  IPurchaseHistoryService{

    @Autowired
    private IPurchaseHistoryRepository purchaseHistoryRepository;

//**************************************************************************************
    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory){

        purchaseHistory.setPurchaseTime(LocalDateTime.now());   //kaydetme kısmı
        return  purchaseHistoryRepository.save(purchaseHistory);

    }

    //************************************************************************************

    @Override
    public List<IPurchaseItem> findPurchasedItemsOfUser(Long userId){
                                                                              //satın alınan kitaplatrı listele
        return  purchaseHistoryRepository.findAllPurchasesOfUser(userId) ;  //listeler
    }

    //*******************************************************************************
















}
