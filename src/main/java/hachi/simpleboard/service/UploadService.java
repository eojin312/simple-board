package hachi.simpleboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
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

    public String multiplefile(MultipartFile uploadingFiles) throws Exception {
        String originalName = uploadingFiles.getOriginalFilename();
        File dest = new File(UPLOAD_BASE_DIR + originalName);
        uploadingFiles.transferTo(dest);

        return originalName;
    }
}
