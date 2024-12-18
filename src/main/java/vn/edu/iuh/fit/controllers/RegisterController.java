package vn.edu.iuh.fit.controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.models.UserAccount;
import vn.edu.iuh.fit.services.UserAccountService;

@Controller
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email, // Thêm email vào
                           @RequestParam("password") String password,
                           @RequestParam("confirmPassword") String confirmPassword,
                           @RequestParam("role") String role,
                           Model model) {

        // Kiểm tra mật khẩu xác nhận có khớp không
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
            return "default/register";
        }

        // Kiểm tra tên đăng nhập có tồn tại không
        if (userAccountService.findByUsername(username) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "default/register";
        }

        // Kiểm tra email có tồn tại không
        if (userAccountService.findByEmail(email) != null) {
            model.addAttribute("error", "Email đã tồn tại!");
            return "default/register";
        }

        // Tạo đối tượng UserAccount và mã hóa mật khẩu
        UserAccount userAccount = new UserAccount();
        String encodedPassword = passwordEncoder.encode(password);
        userAccount.setUsername(username);
        userAccount.setEmail(email);  // Lưu email
        userAccount.setPassword(encodedPassword);
        userAccount.setUserType(role);

        userAccountService.save(userAccount);

       return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "default/register";
    }
}
