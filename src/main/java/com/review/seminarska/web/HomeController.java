package com.review.seminarska.web;

import com.review.seminarska.dto.PostDTO;
import com.review.seminarska.model.Post;
import com.review.seminarska.service.CategoryService;
import com.review.seminarska.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HomeController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/postImages";

    private final CategoryService categoryService;
    private final PostService postService;

    public HomeController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping({"/", "/home","/categories"})
    public String getHomePage() {

        return "categories";
    }

    @GetMapping("/posts/add")
    public String getPostsAdd(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "post-add";
    }

    @PostMapping("/posts/add")
    public String postAdd(@ModelAttribute("postDTO") PostDTO postDTO,
                          @RequestParam("productImage") MultipartFile file,
                          @RequestParam("imgName") String imgName) throws IOException {

        Post post = new Post();
        post.setId(postDTO.getId());
        post.setName(postDTO.getName());
        post.setCategory(categoryService.getCategoryById(postDTO.getCategoryId()).get());
        post.setPrice(postDTO.getPrice());
        post.setDescription(postDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        post.setImageName(imageUUID);
        postService.addPost(post);


        return "redirect:/shop";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("posts", postService.getAllPosts());

        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shop(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("posts", postService.getAllPostsByCategoryId(id));

        return "shop";
    }

    @GetMapping("/shop/viewpost/{id}")
    public String viewPost(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.getPostById(id).get());
        return "viewPost";

    }
}
