package com.how2java.test;
 
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.how2java.mapper.UserMapper;
import com.how2java.pojo.Admin;
import com.how2java.pojo.User;
import com.how2java.service.AdminService; 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyBatisTest {
 
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminService adminService;
 
  //@Test
    public void testAdd() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("new User");
            user.setPassword("123456");
            userMapper.add(user);
        }
 
    }
  //@Test
  public void testSelect() {
	  List<User> us=userMapper.select();
      for (User u : us) {
          System.out.println(u.getName());
      }
        }
  @Test
  public void testSelsetAdmin()
  {
	  Admin ad=adminService.validate_Admin("1");
	  System.out.println(ad.getPassword());
  }

}