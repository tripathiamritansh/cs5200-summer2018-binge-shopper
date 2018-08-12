package neu.edu.bingeshopper.Repository.Model;

public class Product {

    private int id;
    private String name;
    private String description;
    private String image_url;
    private String thumbnail_url;

    public Product() {
    }

    public Product(int id, String name, String description, String image_url, String thumbnail_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.thumbnail_url = thumbnail_url;
    }

    public Product(String name, String description, String image_url) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
