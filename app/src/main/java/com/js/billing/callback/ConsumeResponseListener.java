package com.js.billing.callback;

public interface ConsumeResponseListener {
    void onConsumeResponse(int responseCode, String debugMessage, String s);
}
