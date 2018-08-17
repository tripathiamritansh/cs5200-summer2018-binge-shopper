package neu.edu.bingeshopper.Repository;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.WishList;
import neu.edu.bingeshopper.network.WishListService;
import neu.edu.bingeshopper.presentation.ProductLinearList.ProductLinearListModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LinearListRepository {

    private WishListService wishListService;

    @Inject
    public LinearListRepository(@Named("aws") Retrofit retrofit) {
        wishListService = retrofit.create(WishListService.class);
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

    public void getUserWishList(int userId, final Repository.RepositoryCallBack<LinearListRepositoryResponse> callBack) {

        wishListService.getProductsInWishList(userId).enqueue(new Callback<List<WishList>>() {
            @Override
            public void onResponse(Call<List<WishList>> call, Response<List<WishList>> response) {
                if (response.isSuccessful()) {
                    List<ProductLinearListModel> linearListModel = new ArrayList<>();
                    for (WishList wishList : response.body()) {
                        ProductLinearListModel model = new ProductLinearListModel();
                        model.setProduct(wishList.getProduct());
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
}
