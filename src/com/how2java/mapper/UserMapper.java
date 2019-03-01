package com.how2java.mapper;

import com.how2java.pojo.User;
import java.util.List;
public interface UserMapper {
      public int add(User user);//增加用户
      public List<User> select();     //查询所有用户
}
