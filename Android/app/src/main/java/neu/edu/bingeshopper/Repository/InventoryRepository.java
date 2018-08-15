package neu.edu.bingeshopper.Repository;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.network.SellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InventoryRepository extends Repository {


    private SellerService sellerService;

    public InventoryRepository(@Named("aws") Retrofit retrofit) {
        this.sellerService = retrofit.create(SellerService.class);
    }

    public class InventoryRepositoryResponse implements ResponseValue {
        @Nullable
        private List<Inventory> inventories;
        @Nullable
        private String message;

        public InventoryRepositoryResponse(List<Inventory> inventories, String message) {
            this.inventories = inventories;
            this.message = message;
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


}
