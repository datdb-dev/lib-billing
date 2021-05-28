package com.datdb.billing.callback;

public interface ConsumeResponseListener {
    void onConsumeResponse(int responseCode, String debugMessage, String s);
}
