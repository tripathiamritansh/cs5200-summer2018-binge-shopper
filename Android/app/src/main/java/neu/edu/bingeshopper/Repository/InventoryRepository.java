package neu.edu.bingeshopper.Repository;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.network.InventoryService;
import neu.edu.bingeshopper.network.SellerService;
import neu.edu.bingeshopper.network.WishListService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InventoryRepository extends Repository {


    private SellerService sellerService;
    private WishListService wishListService;

    @Inject
    public InventoryRepository(@Named("aws") Retrofit retrofit) {
        this.wishListService = retrofit.create(WishListService.class);
        this.sellerService = retrofit.create(SellerService.class);
    }

    public class InventoryRepositoryResponse implements ResponseValue {
        @Nullable
        private List<Inventory> inventories;
        @Nullable
        private String message;
        @Nullable
        private Inventory inventory;

        public InventoryRepositoryResponse(List<Inventory> inventories, String message) {
            this.inventories = inventories;
            this.message = message;
        }

        public InventoryRepositoryResponse(String message, Inventory inventory) {
            this.message = message;
            this.inventory = inventory;
        }

        public InventoryRepositoryResponse(String message) {
            this.message = message;
        }

        @Nullable
        public Inventory getInventory() {
            return inventory;
        }

        @Nullable
        public List<Inventory> getInventories() {
            return inventories;
        }

        public void setInventories(@Nullable List<Inventory> inventories) {
            this.inventories = inventories;
        }

        @Nullable
        public String getMessage() {
            return message;
        }

        public void setMessage(@Nullable String message) {
            this.message = message;
        }
    }

    public void addToWishList(int userId, Product product, final RepositoryCallBack callBack) {
        wishListService.addToWishList(userId, product).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(new InventoryRepositoryResponse("Product added to wish list"));
                } else {
                    callBack.onError(new InventoryRepositoryResponse("Product not added to wish list"));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onError(new InventoryRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void getInventoryForProductWithQuatity(int id, final int qty, final RepositoryCallBack callBack) {
        sellerService.getAllSellersForProduct(id).enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                if (response.isSuccessful()) {
                    List<Inventory> inventories = new ArrayList<>();
                    for (Inventory inventory : response.body()) {
                        if (inventory.getQty() >= qty) {
                            inventories.add(inventory);
                        }
                    }
                    callBack.onSuccess(new InventoryRepositoryResponse(inventories, null));
                } else {
                    callBack.onError(new InventoryRepositoryResponse(null, "Error fetching sellers inventories for this product"));
                }
            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                callBack.onError(new InventoryRepositoryResponse(null, t.getMessage()));
            }
        });
    }


    public void addProductToInventory(int userId, int qty, int price, Product product, final RepositoryCallBack callBack) {

        sellerService.addProductToInventory(userId, price, qty, product).enqueue(new Callback<Inventory>() {
            @Override
            public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(new InventoryRepositoryResponse("", response.body()));
                } else {
                    callBack.onError(new InventoryRepositoryResponse("Error Adding Product in Inventory", null));
                }
            }

            @Override
            public void onFailure(Call<Inventory> call, Throwable t) {
                callBack.onError(new InventoryRepositoryResponse(t.getMessage(), null));
            }
        });

    }
}
