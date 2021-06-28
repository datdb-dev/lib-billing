package com.js.billing.callback;


import com.js.billing.model.SkuDetails;

import java.util.List;

public interface SkuDetailsResponseListener {
    void onSkuDetailsResponse(int responseCode, String debugMessage, List<SkuDetails> list);
}
