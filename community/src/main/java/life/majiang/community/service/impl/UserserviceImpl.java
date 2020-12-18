package life.majiang.community.service.impl;

import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserserviceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public String saveOrUpdateUser(GithubUser githubUser) {
        String accountId = githubUser.getId().toString();
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(example);
        User user = null;
        if(users.size() != 0){
            user = users.get(0);
        }
        if(user != null){
            user.setToken(UUID.randomUUID().toString());
            user.setGmtModified(System.currentTimeMillis());
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatar_url());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(user.getId());
            userMapper.updateByExampleSelective(user, userExample);
        }else{
            user = new User();
            user.setAccountId(accountId);
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
        }
        return user.getToken();
    }

    @Override
    public User findByToken(String token) {
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        User user = null;
        if(users.size()!=0){
            user = users.get(0);
        }
        return user;
    }

    @Override
    public User findById(Long userId) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(userId);
        return userMapper.selectByExample(example).get(0);
    }


}
