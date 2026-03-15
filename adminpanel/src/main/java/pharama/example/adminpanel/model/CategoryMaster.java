package pharama.example.adminpanel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "category_master")
@EntityListeners(AuditingEntityListener.class)
public class CategoryMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long category_id;
    
    @Column(name = "category_name")
    private String category_name;

    @Column(name = "category_description")
    private String category_description;

    @Column(name = "category_status")
    private String category_status;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime create_date;

    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modify_date;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductMaster> products;

    // Getters and Setters
    public Long getCategory_id() { return category_id; }
    public void setCategory_id(Long category_id) { this.category_id = category_id; }
    public String getCategory_name() { return category_name; }
    public void setCategory_name(String category_name) { this.category_name = category_name; }
    public String getCategory_description() { return category_description; }
    public void setCategory_description(String category_description) { this.category_description = category_description; }
    public String getCategory_status() { return category_status; }
    public void setCategory_status(String category_status) { this.category_status = category_status; }
    public LocalDateTime getCreate_date() { return create_date; }
    public void setCreate_date(LocalDateTime create_date) { this.create_date = create_date; }
    public LocalDateTime getModify_date() { return modify_date; }
    public void setModify_date(LocalDateTime modify_date) { this.modify_date = modify_date; }
    public List<ProductMaster> getProducts() { return products; }
    public void setProducts(List<ProductMaster> products) { this.products = products; }
}