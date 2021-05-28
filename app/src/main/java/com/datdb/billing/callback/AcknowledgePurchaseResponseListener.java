package com.datdb.billing.callback;

public interface AcknowledgePurchaseResponseListener {
    void onAcknowledgePurchaseResponse(int responseCode, String debugMessage);
}
