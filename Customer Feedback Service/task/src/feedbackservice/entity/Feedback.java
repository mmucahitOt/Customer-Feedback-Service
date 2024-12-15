package feedbackservice.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class Feedback {
    @Id
    private String id;
    // other fields

    private Integer rating;

    private String feedback;

    private String customer;

    private String product;

    private String vendor;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public String getFeedback() {
        return this.feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String  customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

}