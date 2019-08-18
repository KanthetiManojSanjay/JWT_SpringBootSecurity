package rc.bootsecurity.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rc.bootsecurity.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public DBInit(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        this.userRepository.deleteAll();
        User user=new User("sanjay",passwordEncoder.encode("sanjay123"), "USER","");
        User admin=new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager=new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

        List<User> users= Arrays.asList(user,admin,manager);

        this.userRepository.saveAll(users);
    }
}
