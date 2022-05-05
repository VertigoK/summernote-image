package kevin.study.summernoteimage.service;

import kevin.study.summernoteimage.domain.UploadFile;
import kevin.study.summernoteimage.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ImageService {

    private final UploadFileRepository uploadFileRepository;

    private final Path rootLocation;    //c:/image

    @Autowired
    public ImageService(String uploadPath, UploadFileRepository uploadFileRepository) {
        this.rootLocation = Paths.get(uploadPath);
        this.uploadFileRepository = uploadFileRepository;
    }

    public UploadFile store(MultipartFile file) throws Exception {
        //fileName: example.jpg
        //saveFileName: uuidexample.jpg
        //filePath: c:/image/uuidexample.jpg
        //contentType: image/jpeg
        //size: 4994942 (byte)
        //registerDate: 2022-04-30 22:29:57.748
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store an empty file " + file.getOriginalFilename());
            }
            String saveFileName = fileSave(rootLocation.toString(), file);
            UploadFile saveFile = new UploadFile();
            saveFile.setFileName(file.getOriginalFilename());
            saveFile.setSaveFileName(saveFileName);
            saveFile.setContentType(file.getContentType());
            saveFile.setSize(file.getResource().contentLength());
            saveFile.setRegisteredAt(LocalDateTime.now());
            saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') + '/' + saveFileName);
            uploadFileRepository.save(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store a file " + file.getOriginalFilename(), e);
        }
    }

    public UploadFile load(Long fileId) {
        return uploadFileRepository.findById(fileId).orElseThrow();
    }

    public String fileSave(String rootLocation, MultipartFile file) throws IOException {
        File uploadDir = new File(rootLocation);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        //saveFileName 생성
        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid + file.getOriginalFilename();
        File saveFile = new File(rootLocation, saveFileName);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFileName;
    }
}
