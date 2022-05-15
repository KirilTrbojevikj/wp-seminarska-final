package com.review.seminarska.web;

import com.review.seminarska.dto.PostDTO;
import com.review.seminarska.model.Category;
import com.review.seminarska.model.Post;
import com.review.seminarska.service.CategoryService;
import com.review.seminarska.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    private final CategoryService categoryService;
    private final PostService postService;

    public AdminController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping("/admin")
    public String adminHome() {

        return "admin";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories-admin";
    }

    @GetMapping("/admin/categories/add")
    public String getCategoryAdd(Model model) {
        model.addAttribute("category", new Category());
        return "category-add";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoryAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);

        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category-add";
        } else {
            return "404";
        }
    }

    @GetMapping("/admin/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/admin/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.removePostById(id);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/post/update/{id}")
    public String updatePostGet(@PathVariable long id, Model model){
        Post post = postService.getPostById(id).get();
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setName(post.getName());
        postDTO.setCategoryId(post.getCategory().getId());
        postDTO.setPrice(post.getPrice());
        postDTO.setDescription(post.getDescription());
        postDTO.setImageName(post.getImageName());

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("postDTO", postDTO);


        return "postsAdd";
    }



}
