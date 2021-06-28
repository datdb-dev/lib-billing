package com.js.billing.model;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {

    private String jsonSkuDetails;
    private JSONObject json;

    public SkuDetails(String jsonSkuDetails) throws JSONException {
        this.jsonSkuDetails = jsonSkuDetails;
        json = new JSONObject(jsonSkuDetails);
    }

    public int getIntroductoryPriceCycles() {
        return json.optInt("introductoryPriceCycles");
    }

    public int hashCode() {
        return jsonSkuDetails.hashCode();
    }

    public long getIntroductoryPriceAmountMicros() {
        return json.optLong("introductoryPriceAmountMicros");
    }

    public long getOriginalPriceAmountMicros() {
        return json.has("original_price_micros") ? json.optLong("original_price_micros") : this.getPriceAmountMicros();
    }

    public long getPriceAmountMicros() {
        return json.optLong("price_amount_micros");
    }

    
    public String getDescription() {
        return json.optString("description");
    }

    
    public String getFreeTrialPeriod() {
        return json.optString("freeTrialPeriod");
    }

    
    public String getIconUrl() {
        return json.optString("iconUrl");
    }

    
    public String getIntroductoryPrice() {
        return json.optString("introductoryPrice");
    }

    
    public String getIntroductoryPricePeriod() {
        return json.optString("introductoryPricePeriod");
    }

    
    public String getOriginalJson() {
        return jsonSkuDetails;
    }

    
    public String getOriginalPrice() {
        return json.has("original_price") ? json.optString("original_price") : this.getPrice();
    }

    
    public String getPrice() {
        return json.optString("price");
    }

    
    public String getPriceCurrencyCode() {
        return json.optString("price_currency_code");
    }

    
    public String getSku() {
        return json.optString("productId");
    }

    
    public String getSubscriptionPeriod() {
        return json.optString("subscriptionPeriod");
    }

    
    public String getTitle() {
        return json.optString("title");
    }

    
    public String getType() {
        return json.optString("type");
    }
}
