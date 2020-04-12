package life.majiang.community.service;

import life.majiang.community.dto.GithubUser;
import life.majiang.community.model.User;

public interface UserService {

    String saveOrUpdateUser(GithubUser githubUser);

    User findByToken(String token);
}
