package hachi.simpleboard.service;

import hachi.simpleboard.domain.file.FileUpload;
import hachi.simpleboard.domain.file.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    public FileUpload storeFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            FileUpload fileUpload = new FileUpload(fileName, file.getContentType(), file.getBytes());
            return fileUploadRepository.save(fileUpload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileUpload getfile(String fileField) {
        return fileUploadRepository.findAll(fileField);
    }

}
