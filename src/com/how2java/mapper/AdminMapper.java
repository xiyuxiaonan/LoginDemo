package com.how2java.mapper;
import com.how2java.pojo.Admin;
public interface AdminMapper {
      public Admin select(String name);   //根据管理员名字得到一个这个管理员的信息
}
