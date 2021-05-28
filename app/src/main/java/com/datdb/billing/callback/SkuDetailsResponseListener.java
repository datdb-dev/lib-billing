package com.datdb.billing.callback;

import com.datdb.billing.model.SkuDetails;

import java.util.List;

public interface SkuDetailsResponseListener {
    void onSkuDetailsResponse(int responseCode, String debugMessage, List<SkuDetails> list);
}
