package hachi.simpleboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UploadService {

    public static final String UPLOAD_BASE_DIR = "/Users/user/data/";

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public List<String> multiplefile(@RequestParam("uploadingFiles") List<MultipartFile> uploadingFiles) throws IOException {
        List<String> filePathList = new ArrayList<>();
        for (MultipartFile multipartFile : uploadingFiles) {
            String originalName = multipartFile.getOriginalFilename();
            File dest = new File(UPLOAD_BASE_DIR + originalName);
            multipartFile.transferTo(dest);
        }
        return filePathList;
    }
}
