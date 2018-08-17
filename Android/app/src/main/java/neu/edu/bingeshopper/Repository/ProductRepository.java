package neu.edu.bingeshopper.Repository;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import neu.edu.bingeshopper.Repository.Model.ImageEntity;
import neu.edu.bingeshopper.Repository.Model.Item;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.ProductReview;
import neu.edu.bingeshopper.Repository.Model.Repository;
import neu.edu.bingeshopper.Repository.Model.WalmartResponse;
import neu.edu.bingeshopper.network.ProductService;
import neu.edu.bingeshopper.network.WalmartService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository extends Repository {


    private WalmartService walmartService;
    private ProductService productService;

    @Inject
    public ProductRepository(@Named("walmart") Retrofit retrofitWalmart, @Named("aws") Retrofit awsRetrofit) {

        this.walmartService = retrofitWalmart.create(WalmartService.class);
        this.productService = awsRetrofit.create(ProductService.class);
    }


    public class ProductRepositoryResponse implements ResponseValue {
        private String message;
        private List<Product> products;
        private List<ProductReview> productReviews;

        public ProductRepositoryResponse(List<Product> products) {
            this.products = products;
        }

        public ProductRepositoryResponse(String message) {
            this.message = message;
        }


        @Nullable
        public String getMessage() {
            return message;
        }

        @Nullable
        public List<Product> getProducts() {
            return products;
        }
    }

    public void searchProduct(String query, String sort, final RepositoryCallBack callBack) {

        walmartService.searchProducts(query, sort).enqueue(new Callback<WalmartResponse>() {
            @Override
            public void onResponse(Call<WalmartResponse> call, Response<WalmartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = new ArrayList<>();
                    for (Item item : response.body().getItems()) {
                        String name = item.getName();
                        String description = item.getLongDescription();
                        String imageUrl = "";
                        if (item != null && item.getImageEntities() != null) {
                            for (ImageEntity imageEntity : item.getImageEntities()) {
                                if (imageEntity.getEntityType().equals("PRIMARY")) {
                                    if (item.getImageEntities().get(0).getLargeImage() != null) {

                                        imageUrl = imageEntity.getLargeImage();
                                    } else {
                                        imageUrl = imageEntity.getMediumImage();
                                    }                                }
                            }


                            products.add(new Product(item.getItemId(), name, description, imageUrl));
                        }
                    }
                    callBack.onSuccess(new ProductRepositoryResponse(products));
                } else {
                    callBack.onError(new ProductRepositoryResponse("Error Fetching data, Please try again"));
                }
            }

            @Override
            public void onFailure(Call<WalmartResponse> call, Throwable t) {
                callBack.onSuccess(new ProductRepositoryResponse(t.getMessage()));
            }
        });

    }


    public void getProductReview(int id, final RepositoryCallBack callBack) {
        productService.getProductReview(id).enqueue(new Callback<List<ProductReview>>() {
            @Override
            public void onResponse(Call<List<ProductReview>> call, Response<List<ProductReview>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onError(new ProductRepositoryResponse("Error Fetching reviews, Please try again"));
                }

            }

            @Override
            public void onFailure(Call<List<ProductReview>> call, Throwable t) {
                callBack.onError(new ProductRepositoryResponse(t.getMessage()));
            }
        });
    }

}
