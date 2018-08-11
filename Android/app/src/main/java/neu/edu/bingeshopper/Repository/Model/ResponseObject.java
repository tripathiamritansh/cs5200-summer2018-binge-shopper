package neu.edu.bingeshopper.Repository.Model;

import android.support.annotation.Nullable;

public class ResponseObject<R> {
    @Nullable
    private R object;

    @Nullable
    private String exceptionMessage;

    public ResponseObject(R object) {
        this.object = object;
    }

    public ResponseObject(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Nullable
    public R getObject() {
        return object;
    }

    public void setObject(@Nullable R object) {
        this.object = object;
    }

    @Nullable
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(@Nullable String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}