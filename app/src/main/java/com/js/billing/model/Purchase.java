package com.js.billing.model;

import org.json.JSONException;

public class Purchase extends com.android.billingclient.api.Purchase {
    public Purchase(String s, String s1) throws JSONException {
        super(s, s1);
    }
}
