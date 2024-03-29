package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.Article;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.ArticleRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/catmisc")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    private RestTemplate restTemplate;

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
        updateArticle.setContent(article.getContent());
//        updateArticle.setWikipawdia(article.getWikipawdia());

        try{
            articleRepository.save(updateArticle);
            return CatMiscResponse.ok().setPayload(updateArticle);
        }catch (Exception e){
            return CatMiscResponse.ok().addErrorMsgToResponse("Article not updated: ",e);
        }

    }

    @GetMapping("/rndcat")
    public CatMiscResponse handleRequest(Model model) {
        CatMiscResponse responseCat = restTemplate.getForObject("http://cat-microservice/api/cat/descriptions", CatMiscResponse.class);
        System.out.println(responseCat.getPayload());
        Random rand = new Random();
        String random = responseCat.getPayload().toString().replaceAll("=",":");
        System.out.println(random);
        JSONArray jsonArray = new JSONArray(random);
        JSONObject object = jsonArray.getJSONObject(rand.nextInt(jsonArray.length()));
        System.out.println(object);
        return CatMiscResponse.ok().setPayload(object.toString());
    }
}
