package com.datdb.billing.callback;

import com.datdb.billing.model.Purchase;

import java.util.List;

public interface OnBillingListener {

    void onBillingSetupFinished(int responseCode, String debugMessage);

    void onBillingServiceDisconnected();

    void onPurchasesUpdated(int responseCode, String debugMessage, List<Purchase> list);
}
