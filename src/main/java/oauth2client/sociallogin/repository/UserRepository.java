package oauth2client.sociallogin.repository;

import lombok.extern.slf4j.Slf4j;
import oauth2client.sociallogin.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Repository
public class UserRepository {
    private Map<String, Object> users = new ConcurrentHashMap<>();

    public User findByUsername(String username) {
        if(users.containsKey(username)) {
            return (User)users.get(username);
        }
        log.debug("해당 유저를 찾지 못했습니다.");
        return null;
    }

    public void register(User user) {
        if(users.containsKey(user.getUsername())) {
            return;
        }
        users.put(user.getUsername(), user);
        log.debug("{}님이 회원가입 했습니다.", user.getUsername());
    }


}
