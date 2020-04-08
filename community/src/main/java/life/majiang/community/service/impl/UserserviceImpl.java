package life.majiang.community.service.impl;

import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserserviceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public String saveUser(GithubUser githubUser) {
        User user = new User();
        user.setAccountId(githubUser.getId().toString());
        user.setName(githubUser.getName());
        user.setToken(UUID.randomUUID().toString());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setBio(githubUser.getBio());
        user.setAvatarUrl(githubUser.getAvatar_url());
        userMapper.insert(user);
        return user.getToken();
    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }
}
