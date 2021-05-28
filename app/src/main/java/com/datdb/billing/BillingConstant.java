package com.datdb.billing;

import com.android.billingclient.api.BillingClient;

public class BillingConstant {
    public static final int SERVICE_TIMEOUT = BillingClient.BillingResponseCode.SERVICE_TIMEOUT;
    public static final int FEATURE_NOT_SUPPORTED = BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED;
    public static final int SERVICE_DISCONNECTED = BillingClient.BillingResponseCode.SERVICE_DISCONNECTED;
    public static final int OK = BillingClient.BillingResponseCode.OK;
    public static final int USER_CANCELED = BillingClient.BillingResponseCode.USER_CANCELED;
    public static final int SERVICE_UNAVAILABLE = BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE;
    public static final int BILLING_UNAVAILABLE = BillingClient.BillingResponseCode.BILLING_UNAVAILABLE;
    public static final int ITEM_UNAVAILABLE = BillingClient.BillingResponseCode.ITEM_UNAVAILABLE;
    public static final int DEVELOPER_ERROR = BillingClient.BillingResponseCode.DEVELOPER_ERROR;
    public static final int ERROR = BillingClient.BillingResponseCode.ERROR;
    public static final int ITEM_ALREADY_OWNED = BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED;
    public static final int ITEM_NOT_OWNED = BillingClient.BillingResponseCode.ITEM_NOT_OWNED;

    public static final String INAPP = BillingClient.SkuType.INAPP;
    public static final String SUBS = BillingClient.SkuType.SUBS;

    public static final String SUBSCRIPTIONS = BillingClient.FeatureType.SUBSCRIPTIONS;
    public static final String SUBSCRIPTIONS_UPDATE = BillingClient.FeatureType.SUBSCRIPTIONS_UPDATE;
    public static final String IN_APP_ITEMS_ON_VR = BillingClient.FeatureType.IN_APP_ITEMS_ON_VR;
    public static final String SUBSCRIPTIONS_ON_VR = BillingClient.FeatureType.SUBSCRIPTIONS_ON_VR;
    public static final String PRICE_CHANGE_CONFIRMATION = BillingClient.FeatureType.PRICE_CHANGE_CONFIRMATION;
}
