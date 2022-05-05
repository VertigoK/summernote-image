package kevin.study.summernoteimage.repository;

import kevin.study.summernoteimage.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
