package neu.edu.bingeshopper.Repository.Model;

import java.util.ArrayList;

public class Item {
    private int itemId;

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    private int parentItemId;

    public int getParentItemId() {
        return this.parentItemId;
    }

    public void setParentItemId(int parentItemId) {
        this.parentItemId = parentItemId;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double msrp;

    public double getMsrp() {
        return this.msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    private double salePrice;

    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    private String upc;

    public String getUpc() {
        return this.upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    private String categoryPath;

    public String getCategoryPath() {
        return this.categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    private String shortDescription;

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    private String longDescription;

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

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

    private String productTrackingUrl;

    public String getProductTrackingUrl() {
        return this.productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    private double standardShipRate;

    public double getStandardShipRate() {
        return this.standardShipRate;
    }

    public void setStandardShipRate(double standardShipRate) {
        this.standardShipRate = standardShipRate;
    }

    private boolean marketplace;

    public boolean getMarketplace() {
        return this.marketplace;
    }

    public void setMarketplace(boolean marketplace) {
        this.marketplace = marketplace;
    }

    private String modelNumber;

    public String getModelNumber() {
        return this.modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    private String productUrl;

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    private String customerRating;

    public String getCustomerRating() {
        return this.customerRating;
    }

    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating;
    }

    private int numReviews;

    public int getNumReviews() {
        return this.numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    private String customerRatingImage;

    public String getCustomerRatingImage() {
        return this.customerRatingImage;
    }

    public void setCustomerRatingImage(String customerRatingImage) {
        this.customerRatingImage = customerRatingImage;
    }

    private String categoryNode;

    public String getCategoryNode() {
        return this.categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    private String rhid;

    public String getRhid() {
        return this.rhid;
    }

    public void setRhid(String rhid) {
        this.rhid = rhid;
    }

    private boolean bundle;

    public boolean getBundle() {
        return this.bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    private String stock;

    public String getStock() {
        return this.stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    private String addToCartUrl;

    public String getAddToCartUrl() {
        return this.addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    private String affiliateAddToCartUrl;

    public String getAffiliateAddToCartUrl() {
        return this.affiliateAddToCartUrl;
    }

    public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
        this.affiliateAddToCartUrl = affiliateAddToCartUrl;
    }


    private ArrayList<ImageEntity> imageEntities;

    public ArrayList<ImageEntity> getImageEntities() {
        return this.imageEntities;
    }

    public void setImageEntities(ArrayList<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    private String offerType;

    public String getOfferType() {
        return this.offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    private boolean isTwoDayShippingEligible;

    public boolean getIsTwoDayShippingEligible() {
        return this.isTwoDayShippingEligible;
    }

    public void setIsTwoDayShippingEligible(boolean isTwoDayShippingEligible) {
        this.isTwoDayShippingEligible = isTwoDayShippingEligible;
    }

    private boolean availableOnline;

    public boolean getAvailableOnline() {
        return this.availableOnline;
    }

    public void setAvailableOnline(boolean availableOnline) {
        this.availableOnline = availableOnline;
    }
}
