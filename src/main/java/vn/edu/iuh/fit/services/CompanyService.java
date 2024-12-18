package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Company;
import vn.edu.iuh.fit.models.UserAccount;
import vn.edu.iuh.fit.repositories.CompanyRepository;
import vn.edu.iuh.fit.repositories.UserAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
    public Company findNameByUsername(String username) {
        UserAccount acc = userAccountRepository.findByUsername(username);
        Long id = acc.getId();
        return companyRepository.findByUserId(id);
    }
}
