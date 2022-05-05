package kevin.study.summernoteimage.repository;

import kevin.study.summernoteimage.domain.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

}
