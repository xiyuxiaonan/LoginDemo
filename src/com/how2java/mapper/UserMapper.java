package com.how2java.mapper;

import com.how2java.pojo.User;
import java.util.List;
public interface UserMapper {
      public int add(User user);//�����û�
      public List<User> select();     //��ѯ�����û�
}
