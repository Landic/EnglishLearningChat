package itstep.learning.services;
import itstep.learning.entities.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, User> users = new HashMap<>();

    public UserService() {
        User testUser = new User();
        testUser.setId("1");
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPhotoUrl("/images/testuser.jpg");
        testUser.setBio("Пример биографии пользователя");
        users.put(testUser.getId(), testUser);
    }
    public User getUserById(String userId) {
        return users.get(userId);
    }
    public boolean updateUser(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return true;
        }
        return false;
    }
}