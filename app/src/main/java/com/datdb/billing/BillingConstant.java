package com.datdb.billing;

import com.android.billingclient.api.BillingClient;

public class BillingConstant {
    public interface BillingResponseCode {
        int SERVICE_TIMEOUT = BillingClient.BillingResponseCode.SERVICE_TIMEOUT;
        int FEATURE_NOT_SUPPORTED = BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED;
        int SERVICE_DISCONNECTED = BillingClient.BillingResponseCode.SERVICE_DISCONNECTED;
        int OK = BillingClient.BillingResponseCode.OK;
        int USER_CANCELED = BillingClient.BillingResponseCode.USER_CANCELED;
        int SERVICE_UNAVAILABLE = BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE;
        int BILLING_UNAVAILABLE = BillingClient.BillingResponseCode.BILLING_UNAVAILABLE;
        int ITEM_UNAVAILABLE = BillingClient.BillingResponseCode.ITEM_UNAVAILABLE;
        int DEVELOPER_ERROR = BillingClient.BillingResponseCode.DEVELOPER_ERROR;
        int ERROR = BillingClient.BillingResponseCode.ERROR;
        int ITEM_ALREADY_OWNED = BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED;
        int ITEM_NOT_OWNED = BillingClient.BillingResponseCode.ITEM_NOT_OWNED;
    }

    public interface SkuType {
        String INAPP = BillingClient.SkuType.INAPP;
        String SUBS = BillingClient.SkuType.SUBS;
    }

    public interface FeatureType {
        String SUBSCRIPTIONS = BillingClient.FeatureType.SUBSCRIPTIONS;
        String SUBSCRIPTIONS_UPDATE = BillingClient.FeatureType.SUBSCRIPTIONS_UPDATE;
        String IN_APP_ITEMS_ON_VR = BillingClient.FeatureType.IN_APP_ITEMS_ON_VR;
        String SUBSCRIPTIONS_ON_VR = BillingClient.FeatureType.SUBSCRIPTIONS_ON_VR;
        String PRICE_CHANGE_CONFIRMATION = BillingClient.FeatureType.PRICE_CHANGE_CONFIRMATION;
    }
}
