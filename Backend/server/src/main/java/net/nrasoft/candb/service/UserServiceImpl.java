package net.nrasoft.candb.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nrasoft.candb.model.User;
import net.nrasoft.candb.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Set<User> getUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean removeUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return true; // Deletion succeeded
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findUserByEmail(userName);
    }

    @Override
    public boolean isValidUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
