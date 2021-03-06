package com.proaim.service.impl;

import com.proaim.entity.User;
import com.proaim.mapper.UserMapper;
import com.proaim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @date 2019/1/24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getObjectByName(String name) {
        if (!StringUtils.isEmpty(name)) {
            return userMapper.getUserByName(name);
        } else {
            return null;
        }
    }

    @Override
    public List<User> listObjects() {
        return userMapper.listUser();
    }

    @Override
    public User getObjectById(Long id) {
        if (id != null) {
            return userMapper.getUserById(id);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveObject(User user) {
        try {
            if (user != null) {
                userMapper.saveUser(user);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeObjects(Long... ids) {
        try {
            if (ids != null) {
                for (Long id : ids) {
                    userMapper.removeUser(id);
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateObject(User user) {
        try {
            if (user != null) {
                userMapper.updateUser(user);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }
}
