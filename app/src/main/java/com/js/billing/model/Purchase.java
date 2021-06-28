package com.js.billing.model;

import com.android.billingclient.api.zzd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Purchase {
    private String jsonPurchaseInfo;
    private String signature;
    private JSONObject json;

    public Purchase(String jsonPurchaseInfo, String signature) throws JSONException {
        this.jsonPurchaseInfo = jsonPurchaseInfo;
        this.signature = signature;
        json = new JSONObject(jsonPurchaseInfo);
    }

    public boolean isAcknowledged() {
        return json.optBoolean("acknowledged", true);
    }

    public boolean isAutoRenewing() {
        return json.optBoolean("autoRenewing");
    }

    public ArrayList<String> getSkus() {
        ArrayList var1 = new ArrayList();
        if (json.has("productIds")) {
            JSONArray var2 = json.optJSONArray("productIds");
            if (var2 != null) {
                for (int var3 = 0; var3 < var2.length(); ++var3) {
                    var1.add(var2.optString(var3));
                }
            }
        } else if (json.has("productId")) {
            var1.add(json.optString("productId"));
        }

        return var1;
    }

    public int getPurchaseState() {
        switch (json.optInt("purchaseState", 1)) {
            case 4:
                return 2;
            default:
                return 1;
        }
    }

    @zzd
    public int getQuantity() {
        return json.optInt("quantity", 1);
    }


    public long getPurchaseTime() {
        return json.optLong("purchaseTime");
    }


    public String getDeveloperPayload() {
        return json.optString("developerPayload");
    }


    public String getOrderId() {
        return json.optString("orderId");
    }


    public String getOriginalJson() {
        return jsonPurchaseInfo;
    }


    public String getPackageName() {
        return json.optString("packageName");
    }


    public String getPurchaseToken() {
        JSONObject var1 = json;
        return var1.optString("token", var1.optString("purchaseToken"));
    }


    public String getSignature() {
        return signature;
    }
}
