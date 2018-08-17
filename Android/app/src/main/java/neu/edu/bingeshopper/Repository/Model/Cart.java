package neu.edu.bingeshopper.Repository.Model;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.common.Constants;

public class Cart {

    private SharedPreferences sharedPreferences;

    @Inject
    public Cart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public void clearCart() {
        saveInSharePref(new ArrayList<CartItem>());
    }

    public boolean saveToCart(CartItem item) {
        List<CartItem> cartItem = getCartItems();
        for (CartItem cItem : cartItem) {
            if ((cItem.getProduct().getId() == item.getProduct().getId()) && (cItem.getSeller().getId() == item.getSeller().getId())) {
                if (((cItem.getQty() - cItem.getInventory().getQty()) == 0)) {
                    return false;
                }
                cItem.setQty(cItem.getQty() + item.getQty());
                saveInSharePref(cartItem);
                return true;
            }
        }
        cartItem.add(item);
        saveInSharePref(cartItem);
        return true;
    }

    public int getCartTotal() {
        List<CartItem> cartItems = getCartItems();
        int total = 0;
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getQty() * cartItem.getInventory().getPrice());
        }
        return total;
    }

    public boolean removeFromCart(CartItem item) {
        List<CartItem> cartItems = getCartItems();
        boolean isRemoved = cartItems.remove(item);
        if (isRemoved) {
            saveInSharePref(cartItems);
        }
        return isRemoved;
    }

    public void saveInSharePref(List<CartItem> cartItems) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Type sectionListType = new TypeToken<List<CartItem>>() {
        }.getType();
        String json = new Gson().toJson(cartItems, sectionListType);
        editor.putString(Constants.SHARED_PREF_CART, json);
        editor.apply();
    }

    @Nullable
    public List<CartItem> getCartItems() {
        String userJson = sharedPreferences.getString(Constants.SHARED_PREF_CART, null);
        Gson gson = new Gson();
        if (userJson == null || userJson.isEmpty()) {
            return new ArrayList<CartItem>();
        } else {
            Type type = new TypeToken<List<CartItem>>() {
            }.getType();
            return gson.fromJson(userJson, type);
        }
    }
}
