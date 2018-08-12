package neu.edu.bingeshopper.Repository.Model;

import java.util.ArrayList;

public class WalmartResponse {
    private String query;

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String sort;

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    private String responseGroup;

    public String getResponseGroup() {
        return this.responseGroup;
    }

    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup;
    }

    private int totalResults;

    public int getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    private int start;

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    private int numItems;

    public int getNumItems() {
        return this.numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
