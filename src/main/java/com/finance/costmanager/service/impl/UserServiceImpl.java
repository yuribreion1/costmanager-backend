package com.finance.costmanager.service.impl;

import com.finance.costmanager.exception.AuthenticationRuleException;
import com.finance.costmanager.exception.BusinessRuleException;
import com.finance.costmanager.model.User;
import com.finance.costmanager.repository.UserRepository;
import com.finance.costmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User auth(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) throw new AuthenticationRuleException("User not registered!");
        if (!user.get().getPassword().equals(password)) throw new AuthenticationRuleException("Password incorrect");

        return user.get();
    }

    @Override
    @Transactional
    public User entry(User user) {
        validateEmail(user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void validateEmail(String email) {
        boolean existEmail = userRepository.existsByEmail(email);
        if (existEmail) throw new BusinessRuleException("There an user already using this e-mail");
    }
}
