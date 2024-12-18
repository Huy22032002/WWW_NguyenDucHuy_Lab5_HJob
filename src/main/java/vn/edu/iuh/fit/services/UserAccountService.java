package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.UserAccount;
import vn.edu.iuh.fit.repositories.UserAccountRepository;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public void save(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }
}
