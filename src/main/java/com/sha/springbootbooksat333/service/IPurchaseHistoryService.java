package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.PurchaseHistory;
import com.sha.springbootbooksat333.repository.projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {

    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);



    List<IPurchaseItem> findPurchasedItemsOfUser(Long userId);
}
