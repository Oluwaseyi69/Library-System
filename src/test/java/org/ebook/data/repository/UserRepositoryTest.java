package org.ebook.data.repository;
import org.ebook.data.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testThat_I_can_saveInRepository(){
        User user = new User();
        userRepository.save(user);
        assertThat(user.getId()).isNotNull();

    }
}