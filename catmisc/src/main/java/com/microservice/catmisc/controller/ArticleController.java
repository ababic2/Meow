package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.Article;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles") // done
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/articles/{id}") // done
    public CatMiscResponse getArticles(@PathVariable Long id) {
        try {
            Article findArticle = articleRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findArticle);
        }catch (Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Article with id "+id+" was not found", e);
        }
    }


    @PostMapping("/articles") // done
    public Article createArticle(@Valid @RequestBody Article article) {
        return articleRepository.save(article);
    }

    @DeleteMapping("/articles/{id}") // done
    public CatMiscResponse deleteArticle(@PathVariable Long id) {
        try {
            articleRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("Article deleted");
        }catch(Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting article ",e);
        }
    }

    @PutMapping("/articles/{id}") // done
    public CatMiscResponse updateArticle(@PathVariable Long id ,@Valid @RequestBody Article article) {

        Article updateArticle = articleRepository.findById(id).get();

        updateArticle.setTitle(article.getTitle());
        updateArticle.setCatBreed(article.getCatBreed());
        updateArticle.setContent(article.getContent());
        updateArticle.setWikipawdia(article.getWikipawdia());

        try{
            articleRepository.save(updateArticle);
            return CatMiscResponse.ok().setPayload(updateArticle);
        }catch (Exception e){
            return CatMiscResponse.ok().addErrorMsgToResponse("Article not updated: ",e);
        }

    }

}
