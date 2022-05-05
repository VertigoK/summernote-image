package kevin.study.summernoteimage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadFileConfig {

    @Bean(name = "uploadPath")
    public String uploadPath() {
        return "c:/image/";
    }
}
