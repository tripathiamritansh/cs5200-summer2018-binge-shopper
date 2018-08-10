package neu.edu.bingeshopper.Repository.Model;

public abstract class Repository {

    private RepositoryCallBack callBack;


    public void setCallBack(RepositoryCallBack callBack) {
        this.callBack = callBack;
    }

    public RepositoryCallBack getCallBack() {
        return callBack;
    }

    public interface ResponseValue {
    }


    public interface RepositoryCallBack<R> {
        void onSuccess(R response);

        void onError(R response);
    }
}
