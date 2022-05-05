package kevin.study.summernoteimage.controller;

import kevin.study.summernoteimage.domain.Article;
import kevin.study.summernoteimage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article")
    public String getArticleList(Model model) {
        List<Article> articleList = articleService.findArticleList();
        model.addAttribute("articleList", articleList);
        return "article/list";
    }

    @GetMapping("/article/write")
    public String moveToWriteForm() {
        return "article/write";
    }

    @PostMapping("/article/write")
    public String setArticle(Article article) {
        articleService.saveArticle(article);
        return "redirect:/article/" + article.getId();
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = articleService.findArticle(id);
        model.addAttribute("article", article);
        return "article/detail";
    }

    @GetMapping("/article/{id}/update")
    public String getArticleUpdate(@PathVariable Long id, Model model) {
        Article article = articleService.findArticle(id);
        model.addAttribute("article", article);
        return "article/update";
    }

    @PostMapping("/article/{id}/update")
    public String setArticleUpdate(Article updatedArticle) {
        articleService.updateArticle(updatedArticle);
        return "redirect:/article/" + updatedArticle.getId();
    }
}
