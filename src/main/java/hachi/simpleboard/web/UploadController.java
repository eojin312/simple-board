package hachi.simpleboard.web;

import hachi.simpleboard.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

/**
 * 파일 업로드 테스트를 위해 만든 controller
 */
@RestController
@RequiredArgsConstructor
public class UploadController {

    public static final String UPLOAD_BASE_DIR = "/Users/user/data/";
    private final UploadService uploadService;

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    @PostMapping("/api/upload")
    public String upload(@RequestParam("attach-file") MultipartFile multipartFile) throws IOException {

        String originalName = multipartFile.getOriginalFilename();
        String extension = this.getExtensionByStringHandling(originalName).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "확장자가 없는 파일은 업로드할 수 없습니다."));
        String destinationFileName = UUID.nameUUIDFromBytes(multipartFile.getBytes()).toString() + "." + extension;
        String destinationFilePathAndName = UPLOAD_BASE_DIR + destinationFileName;
        File destinationFile = new File(destinationFilePathAndName);
        multipartFile.transferTo(destinationFile);
        return destinationFileName;
    }

    @GetMapping("/api/download")
    public ResponseEntity<Resource> download(@RequestParam("file-name") String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_BASE_DIR + fileName);

        //file로 부터 contentType 을 얻어온다.
        String contentType = Files.probeContentType(path);

        // 헤더를 만든다
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);

        // 인풋스트림리소스를 만든다.
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        // 리턴
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }
}
