package itstep.learning.services;
import itstep.learning.dao.ContentDao;
import itstep.learning.entities.MainContent;
import java.util.List;
import javax.inject.Inject;

public class SimilarContentService {
    private ContentDao contentDao;

    @Inject public SimilarContentService() {
        this.contentDao = contentDao;
    }
    public List<MainContent> getSimilarContent(MainContent content) {
        return contentDao.findSimilarContent(content);
    }
}