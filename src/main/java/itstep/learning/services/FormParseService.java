package itstep.learning.services;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import java.util.List;
import java.util.ArrayList;

public class FormParseService {
    public List<FileItem> parseForm(HttpServletRequest request) throws FileUploadException {
        if (!ServletFileUpload.isMultipartContent(request)) throw new FileUploadException("Форма не является многочастной!");

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        return items != null ? items : new ArrayList<>();
    }
}