package kevin.study.summernoteimage.service;

import kevin.study.summernoteimage.domain.Article;
import kevin.study.summernoteimage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void saveArticle(Article article) {
        article.setRegisteredAt(LocalDateTime.now());
        articleRepository.save(article);
    }

    @Transactional
    public void updateArticle(Article updatedArticle) {
        Article persistenceArticle = articleRepository.findById(updatedArticle.getId()).orElseThrow();
        persistenceArticle.setTitle(updatedArticle.getTitle());
        persistenceArticle.setContent(updatedArticle.getContent());
        persistenceArticle.setUpdatedAt(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public Article findArticle(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Article> findArticleList() {
        return articleRepository.findAll();
    }
}
