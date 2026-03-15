package pharama.example.adminpanel.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_master")
@EntityListeners(AuditingEntityListener.class)
public class ProductMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    
    private String product_name;

    @Column(columnDefinition = "LONGTEXT") // Ensures the Base64 PC image fits in the DB
    private String product_image;
    
    @Column(columnDefinition = "TEXT") // Allows for longer medicine descriptions
    private String product_description;
    
    private String product_status;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime create_date;

    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modify_date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryMaster category;

    // Getters and Setters
    public Long getProduct_id() { return product_id; }
    public void setProduct_id(Long product_id) { this.product_id = product_id; }
    
    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }
    
    public String getProduct_image() { return product_image; }
    public void setProduct_image(String product_image) { this.product_image = product_image; }
    
    public String getProduct_description() { return product_description; }
    public void setProduct_description(String product_description) { this.product_description = product_description; }
    
    public String getProduct_status() { return product_status; }
    public void setProduct_status(String product_status) { this.product_status = product_status; }
    
    public CategoryMaster getCategory() { return category; }
    public void setCategory(CategoryMaster category) { this.category = category; }
    
    public LocalDateTime getCreate_date() { return create_date; }
    public void setCreate_date(LocalDateTime create_date) { this.create_date = create_date; }
    
    public LocalDateTime getModify_date() { return modify_date; }
    public void setModify_date(LocalDateTime modify_date) { this.modify_date = modify_date; }
}