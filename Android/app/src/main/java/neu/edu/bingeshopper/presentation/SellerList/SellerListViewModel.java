package neu.edu.bingeshopper.presentation.SellerList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import neu.edu.bingeshopper.Repository.InventoryRepository;
import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Repository;

public class SellerListViewModel extends ViewModel {

    private InventoryRepository repository;
    private MutableLiveData<SellerListViewModelResponse> mutableLiveData;

    @Inject
    public SellerListViewModel(InventoryRepository repository) {
        this.repository = repository;
    }


    public MutableLiveData<SellerListViewModelResponse> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void getInventoryList(int id, int qty) {
        repository.getInventoryForProductWithQuatity(id, qty, new Repository.RepositoryCallBack<InventoryRepository.InventoryRepositoryResponse>() {
            @Override
            public void onSuccess(InventoryRepository.InventoryRepositoryResponse response) {
                mutableLiveData.setValue(new SellerListViewModelResponse(response.getInventories(), "", Status.SUCCESS));
            }

            @Override
            public void onError(InventoryRepository.InventoryRepositoryResponse response) {
                mutableLiveData.setValue(new SellerListViewModelResponse(null, response.getMessage(), Status.ERROR));
            }
        });
    }

    class SellerListViewModelResponse {
        private List<Inventory> inventories;
        private String messgae;
        private Status status;

        public SellerListViewModelResponse(List<Inventory> inventories, String messgae, Status status) {
            this.inventories = inventories;
            this.messgae = messgae;
            this.status = status;
        }

        public List<Inventory> getInventories() {
            return inventories;
        }

        public Status getStatus() {
            return status;
        }

        public String getMessgae() {
            return messgae;
        }
    }

    enum Status {
        SUCCESS,
        ERROR
    }

}
