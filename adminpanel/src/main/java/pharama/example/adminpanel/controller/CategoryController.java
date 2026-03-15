package pharama.example.adminpanel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pharama.example.adminpanel.model.CategoryMaster;
import pharama.example.adminpanel.repository.CategoryRepository;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired 
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/list";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("category", new CategoryMaster());
        return "categories/form";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        CategoryMaster category = categoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("category", category);
        return "categories/form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") CategoryMaster category) {
        // Hibernate automatically checks if category_id exists to Update or Insert
        categoryRepository.save(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories/list";
    }
}