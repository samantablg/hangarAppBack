package com.myApp;

import com.myApp.app.Application;
import com.myApp.security.model.UserApp;
import com.myApp.security.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void UserRepositoryTest() {
        UserApp user = new UserApp();
        user.setUsername("gabriel");
        user.setPassword(encoder.encode("gabriel"));
        UserApp uSave = userRepository.save(user);

        assertTrue(uSave.getPassword().equalsIgnoreCase(user.getPassword()));
    }

}