package neu.edu.bingeshopper.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.CartItem;
import neu.edu.bingeshopper.Repository.Model.Order;
import neu.edu.bingeshopper.Repository.Model.OrderTransactionWrapper;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.network.OrderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderRepository extends Repository {

    private OrderService orderService;

    @Inject
    public OrderRepository(@Named("aws") Retrofit retrofit) {
        orderService = retrofit.create(OrderService.class);
    }


    public class OrderRepositoryResponse implements ResponseValue {
        private List<Order> orders = new ArrayList<>();
        private String message = "";

        public OrderRepositoryResponse(List<Order> orders, String message) {
            this.orders = orders;
            this.message = message;
        }

        public OrderRepositoryResponse(String message) {
            this.message = message;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public String getMessage() {
            return message;
        }
    }


    public void placeOrder(List<CartItem> cartItems, int userId, final RepositoryCallBack<OrderRepositoryResponse> callBack) {
        List<OrderTransactionWrapper> orderTransactionWrappers = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            orderTransactionWrappers.add(new OrderTransactionWrapper(cartItem.getProduct().getId(),
                    cartItem.getSeller().getId(), cartItem.getQty()));
        }

        orderService.placeOrder(userId, orderTransactionWrappers).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    List<Order> orderList = new ArrayList<>();
                    orderList.add(response.body());
                    callBack.onSuccess(new OrderRepositoryResponse(orderList, "Order was successfully placed"));
                } else {
                    callBack.onError(new OrderRepositoryResponse("Error placing order"));
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                callBack.onError(new OrderRepositoryResponse(t.getMessage()));
            }
        });
    }
}
