package itstep.learning.servlets;
import itstep.learning.services.FormParseService;
import itstep.learning.services.StorageService;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig public class FileUploadServlet extends HttpServlet {
    private FormParseService formParseService = new FormParseService();
    private final StorageService storageService = new StorageService();

    public FormParseService getFormParseService() { return formParseService; }
    public void setFormParseService(FormParseService formParseService) { this.formParseService = formParseService; }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            String fileName = storageService.saveFile(filePart);
            response.getWriter().write("Файл загружен: " + fileName);
        } catch (Exception e) {
            response.getWriter().write("Ошибка загрузки файла: " + e.getMessage());
        }
    }
}