package pharama.example.adminpanel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
import pharama.example.adminpanel.model.ProductMaster;
import pharama.example.adminpanel.repository.ProductRepository;
import pharama.example.adminpanel.repository.CategoryRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("product", new ProductMaster());
        // ISSUE 1 FIX: Filter categories to only show "Active" ones in the dropdown
        model.addAttribute("categories", categoryRepository.findAll().stream()
            .filter(c -> "Active".equalsIgnoreCase(c.getCategory_status()))
            .collect(Collectors.toList()));
        return "products/form";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        ProductMaster product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        // Filter categories for the edit screen dropdown as well
        model.addAttribute("categories", categoryRepository.findAll().stream()
            .filter(c -> "Active".equalsIgnoreCase(c.getCategory_status()))
            .collect(Collectors.toList()));
        return "products/form";
    }

    @GetMapping("/delete/{id}")
    @Transactional // Ensures the row is physically removed from the DB immediately
    public String deleteProduct(@PathVariable("id") Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        return "redirect:/products/list";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") ProductMaster product,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        
        // ISSUE 2 FIX: Handle PC File Upload (Convert to Base64)
        if (imageFile != null && !imageFile.isEmpty()) {
            String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
            product.setProduct_image(base64Image);
        } else if (product.getProduct_id() != null) {
            // When editing, if no new file is selected, keep the existing image from DB
            productRepository.findById(product.getProduct_id()).ifPresent(existing -> {
                product.setProduct_image(existing.getProduct_image());
            });
        }

        productRepository.save(product);
        return "redirect:/products/list";
    }
}