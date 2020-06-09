package me.hughwang.shirotesting.servvice;

import me.hughwang.shirotesting.entities.UserEntity;
import me.hughwang.shirotesting.vo.UserVO;

/**
 * @author :  Hugh Wang https://hughwang.me
 * @date : 2020/6/9 15:21
 */
public interface UserService {

    UserEntity findUserByUsername(UserVO userVO);
}
