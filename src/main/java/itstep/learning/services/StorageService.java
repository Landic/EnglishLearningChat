package itstep.learning.services;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.Part;

public class StorageService {
    private static final String UPLOAD_DIRECTORY = "uploads/";

    public String saveFile(Part filePart) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        Path uploadPath = Paths.get(UPLOAD_DIRECTORY).toAbsolutePath();

        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        filePart.write(uploadPath.resolve(fileName).toString());
        return fileName;
    }
    public File getFile(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIRECTORY).toAbsolutePath().resolve(fileName);
        return filePath.toFile();
    }
}