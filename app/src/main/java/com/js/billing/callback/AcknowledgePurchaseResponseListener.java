package com.js.billing.callback;

public interface AcknowledgePurchaseResponseListener {
    void onAcknowledgePurchaseResponse(int responseCode, String debugMessage);
}
