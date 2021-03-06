package neu.edu.bingeshopper.Repository;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Order;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.Transaction;
import neu.edu.bingeshopper.Repository.Model.WishList;
import neu.edu.bingeshopper.network.InventoryService;
import neu.edu.bingeshopper.network.OrderService;
import neu.edu.bingeshopper.network.TransactionService;
import neu.edu.bingeshopper.network.WishListService;
import neu.edu.bingeshopper.presentation.ProductLinearList.ProductLinearListModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LinearListRepository {

    private InventoryService inventoryService;
    private WishListService wishListService;
    private OrderService orderService;
    private TransactionService transactionService;

    @Inject
    public LinearListRepository(@Named("aws") Retrofit retrofit) {

        wishListService = retrofit.create(WishListService.class);
        inventoryService = retrofit.create(InventoryService.class);
        orderService = retrofit.create(OrderService.class);
        transactionService = retrofit.create(TransactionService.class);

    }


    public class LinearListRepositoryResponse implements Repository.ResponseValue {

        @Nullable
        private List<ProductLinearListModel> data;

        @Nullable
        private String message;

        public LinearListRepositoryResponse(List<ProductLinearListModel> data) {
            this.data = data;
        }


        public LinearListRepositoryResponse(String message) {
            this.message = message;
        }

        @Nullable
        public List<ProductLinearListModel> getData() {
            return data;
        }

        public void setData(@Nullable List<ProductLinearListModel> data) {
            this.data = data;
        }

        @Nullable
        public String getMessage() {
            return message;
        }

        public void setMessage(@Nullable String message) {
            this.message = message;
        }
    }

    public void getTransactionForOrder(int orderId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {
        transactionService.getTransactionsForOrder(orderId).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful()) {
                    List<ProductLinearListModel> modelList = new ArrayList<>();
                    for (Transaction transaction : response.body()) {
                        ProductLinearListModel model = new ProductLinearListModel();
                        model.setTransaction(transaction);
                        modelList.add(model);
                    }
                    callBack.onSuccess(new LinearListRepositoryResponse(modelList));
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error fetching the transaction list"));
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void getOrderList(int userId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {
        orderService.getOrders(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    List<ProductLinearListModel> modelList = new ArrayList<>();
                    for (Order order : response.body()) {
                        ProductLinearListModel model = new ProductLinearListModel();
                        model.setOrder(order);
                        modelList.add(model);
                    }
                    callBack.onSuccess(new LinearListRepositoryResponse(modelList));
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error fetching the order list"));
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void getSellerInventory(int userId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {
        inventoryService.getInventory(userId).enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                if (response.isSuccessful()) {
                    List<ProductLinearListModel> data = new ArrayList<>();
                    for (Inventory inventory : response.body()) {
                        ProductLinearListModel model = new ProductLinearListModel();
                        model.setInventory(inventory);
                        data.add(model);
                    }
                    callBack.onSuccess(new LinearListRepositoryResponse(data));
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error fetch seller inventory"));
                }
            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void getUserWishList(int userId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {

        wishListService.getProductsInWishList(userId).enqueue(new Callback<List<WishList>>() {
            @Override
            public void onResponse(Call<List<WishList>> call, Response<List<WishList>> response) {
                if (response.isSuccessful()) {
                    List<ProductLinearListModel> linearListModel = new ArrayList<>();
                    for (WishList wishList : response.body()) {
                        ProductLinearListModel model = new ProductLinearListModel();
                        model.setProduct(wishList.getProduct());
                        model.setBuyer(wishList.getUser());
                        linearListModel.add(model);
                    }
                    callBack.onSuccess(new LinearListRepositoryResponse(linearListModel));
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error fetching wish list"));
                }
            }

            @Override
            public void onFailure(Call<List<WishList>> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));

            }

        });
    }

    public void deleteFromInventory(int inventoryId, final int sellerId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {
        inventoryService.deleteInventory(inventoryId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getSellerInventory(sellerId, callBack);
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error deleting from inventory"));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void deleteFromWishList(final int userId, Product product, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {
        wishListService.deleteProductFromWishList(userId, product).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getUserWishList(userId, callBack);
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error deleting from wishlist"));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));
            }
        });
    }

    public void deleteOrder(int orderId, final int userId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {
        orderService.deleteOrder(orderId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getOrderList(userId, callBack);
                } else {
                    callBack.onError(new LinearListRepositoryResponse("Error deleting from order"));

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onError(new LinearListRepositoryResponse(t.getMessage()));
            }
        });
    }
}
