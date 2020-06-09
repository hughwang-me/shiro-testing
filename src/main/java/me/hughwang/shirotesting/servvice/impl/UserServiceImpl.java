package me.hughwang.shirotesting.servvice.impl;

import lombok.extern.slf4j.Slf4j;
import me.hughwang.shirotesting.entities.UserEntity;
import me.hughwang.shirotesting.servvice.UserService;
import me.hughwang.shirotesting.vo.UserVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :  Hugh Wang https://hughwang.me
 * @date : 2020/6/9 15:23
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public UserEntity findUserByUsername(UserVO userVO) {
        List<UserEntity> userEntities = jdbcTemplate.
                queryForList("select * from users where user_name = '" + userVO.getUsername() + "'", UserEntity.class);
        for (UserEntity userEntity : userEntities) {
            log.warn("u-> {}" , userEntity.toString());
        }
        return null;
    }
}
