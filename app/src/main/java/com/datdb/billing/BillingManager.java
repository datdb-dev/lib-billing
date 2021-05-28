package com.datdb.billing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetailsParams;

import com.datdb.billing.callback.AcknowledgePurchaseResponseListener;
import com.datdb.billing.callback.ConsumeResponseListener;
import com.datdb.billing.callback.OnBillingListener;
import com.datdb.billing.callback.SkuDetailsResponseListener;
import com.datdb.billing.model.Purchase;
import com.datdb.billing.model.SkuDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class BillingManager {

    private static BillingManager instance;
    private BillingClient billingClient;

    private ArrayList<OnBillingListener> onBillingListeners;

    private BillingManager() {
        onBillingListeners = new ArrayList<>();
    }

    public static void init() {
        instance = new BillingManager();
    }

    public static BillingManager getInstance() {
        return instance;
    }

    private final PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<com.android.billingclient.api.Purchase> list) {
            for (OnBillingListener listener : onBillingListeners) {
                List<Purchase> listPurchase = new ArrayList<>((Collection<? extends Purchase>) list);
                listener.onPurchasesUpdated(billingResult.getResponseCode(), billingResult.getDebugMessage(), listPurchase);
            }
        }
    };

    private final BillingClientStateListener billingClientStateListener = new BillingClientStateListener() {
        @Override
        public void onBillingSetupFinished(BillingResult billingResult) {
            for (OnBillingListener listener :
                    onBillingListeners) {
                listener.onBillingSetupFinished(billingResult.getResponseCode(), billingResult.getDebugMessage());
            }
        }

        @Override
        public void onBillingServiceDisconnected() {
            for (OnBillingListener listener :
                    onBillingListeners) {
                listener.onBillingServiceDisconnected();
            }
        }
    };

    public void startConnection(Context context) {
        if (billingClient == null || !billingClient.isReady()) {
            billingClient = BillingClient.newBuilder(context).enablePendingPurchases().setListener(purchasesUpdatedListener).build();
            billingClient.startConnection(billingClientStateListener);
        }
    }

    public void endConnection() {
        if (billingClient != null) {
            billingClient.endConnection();
        }
        onBillingListeners.clear();
    }

    public void launchBillingFlow(Activity activity, SkuDetails skuDetails) {
        if (billingClient.isReady()) {
            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(skuDetails)
                    .build();
            billingClient.launchBillingFlow(activity, billingFlowParams);
        }
    }

    public void addListener(OnBillingListener onBillingListener) {
        onBillingListeners.add(onBillingListener);
    }

    public void removeListener(OnBillingListener onBillingListener) {
        onBillingListeners.remove(onBillingListener);
    }

    /**
     * @param skuType BillingClient.SkuType.INAPP , BillingClient.SkuType.SUBS
     */
    public void querySkuDetailsAsync(ArrayList<String> listId, String skuType, SkuDetailsResponseListener listener) {
        if (billingClient.isReady()) {
            SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder()
                    .setSkusList(listId).setType(skuType).build();
            billingClient.querySkuDetailsAsync(skuDetailsParams, new com.android.billingclient.api.SkuDetailsResponseListener() {

                @Override
                public void onSkuDetailsResponse(BillingResult billingResult, List<com.android.billingclient.api.SkuDetails> list) {
                    List<SkuDetails> listPurchase = new ArrayList<>((Collection<? extends SkuDetails>) list);
                    listener.onSkuDetailsResponse(billingResult.getResponseCode(), billingResult.getDebugMessage(), listPurchase);
                }
            });
        }
    }

    public void acknowledgePurchase(List<Purchase> purchases, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (billingClient.isReady()) {
            AcknowledgePurchaseParams.Builder builder = AcknowledgePurchaseParams.newBuilder();
            for (Purchase p : purchases) {
                if (!p.isAcknowledged()) {
                    builder.setPurchaseToken(p.getPurchaseToken());
                }
            }
            billingClient.acknowledgePurchase(builder.build(), new com.android.billingclient.api.AcknowledgePurchaseResponseListener() {
                @Override
                public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                    acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult.getResponseCode(), billingResult.getDebugMessage());
                }
            });
        }
    }

    public void acknowledgePurchase(Purchase purchase, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (!purchase.isAcknowledged() && billingClient.isReady()) {
            AcknowledgePurchaseParams purchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.getPurchaseToken()).build();
            billingClient.acknowledgePurchase(purchaseParams, new com.android.billingclient.api.AcknowledgePurchaseResponseListener() {
                @Override
                public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                    acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult.getResponseCode(), billingResult.getDebugMessage());
                }
            });
        }
    }

    public void consumeAsync(List<Purchase> purchases, ConsumeResponseListener consumeResponseListener) {
        if (billingClient.isReady()) {
            ConsumeParams.Builder builder = ConsumeParams.newBuilder();
            for (Purchase p : purchases) {
                builder.setPurchaseToken(p.getPurchaseToken());
            }
            billingClient.consumeAsync(builder.build(), new com.android.billingclient.api.ConsumeResponseListener() {
                @Override
                public void onConsumeResponse(BillingResult billingResult, String s) {
                    consumeResponseListener.onConsumeResponse(billingResult.getResponseCode(), billingResult.getDebugMessage(), s);
                }
            });
        }
    }

    public void consumeAsync(Purchase purchase, ConsumeResponseListener consumeResponseListener) {
        if (billingClient.isReady()) {
            ConsumeParams.Builder builder = ConsumeParams.newBuilder();
            builder.setPurchaseToken(purchase.getPurchaseToken());
            billingClient.consumeAsync(builder.build(), new com.android.billingclient.api.ConsumeResponseListener() {
                @Override
                public void onConsumeResponse(BillingResult billingResult, String s) {
                    consumeResponseListener.onConsumeResponse(billingResult.getResponseCode(), billingResult.getDebugMessage(), s);
                }
            });
        }
    }

    /**
     * @param skyType BillingClient.SkuType.INAPP, SUBS
     * @return
     */
    public List<Purchase> queryPurchases(String skyType) {
        if (billingClient.isReady()) {
            Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(skyType);
            if (purchasesResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                return new ArrayList<>((Collection<? extends Purchase>) purchasesResult.getPurchasesList());
            }
        }
        return new ArrayList<>();
    }

    public boolean checkPurchase(String type, String id) {
        List<Purchase> purchases = queryPurchases(type);
        for (Purchase p : purchases) {
            if (p.getSku().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
