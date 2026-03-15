package pharama.example.adminpanel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pharama.example.adminpanel.repository.CategoryRepository;
import pharama.example.adminpanel.repository.ProductRepository;

@Controller
public class AdminController {

    @Autowired private CategoryRepository categoryRepo;
    @Autowired private ProductRepository productRepo;

    @GetMapping("/")
    public String viewDashboard(Model model) {
        // Send counts to the dashboard
        model.addAttribute("totalCategories", categoryRepo.count());
        model.addAttribute("totalProducts", productRepo.count());
        
        // Send the list of products to show on the dashboard table
        model.addAttribute("products", productRepo.findAll());
        
        return "dashboard"; // This looks for dashboard.html
    }
}