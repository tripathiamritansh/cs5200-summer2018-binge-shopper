package neu.edu.bingeshopper.Repository.Model;

public class ImageEntity {
    private String thumbnailImage;

    public String getThumbnailImage() {
        return this.thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    private String mediumImage;

    public String getMediumImage() {
        return this.mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    private String largeImage;

    public String getLargeImage() {
        return this.largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    private String entityType;

    public String getEntityType() {
        return this.entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}