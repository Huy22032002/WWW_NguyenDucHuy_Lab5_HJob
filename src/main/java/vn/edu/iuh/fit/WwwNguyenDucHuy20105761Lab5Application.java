package vn.edu.iuh.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.edu.iuh.fit.models.UserAccount;
import vn.edu.iuh.fit.repositories.UserAccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class WwwNguyenDucHuy20105761Lab5Application {

    public static void main(String[] args) {
        SpringApplication.run(WwwNguyenDucHuy20105761Lab5Application.class, args);
       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       // String encodedPassword = passwordEncoder.encode("123");
       // System.out.println(encodedPassword);


    }

}
