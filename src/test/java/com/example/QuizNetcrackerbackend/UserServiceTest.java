package com.example.QuizNetcrackerbackend;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

//	@Autowired
//	private UserRepository userRepository;
//
//	@Test
//	@Rollback(value = false)
//	public void testCteateUser() {
//		User user = new User ("testUser", "testUser@mail.ru","testUser");
//		User savedUser = userRepository.save(user);
//
//		//assert (savedUser);
//	}
//
//	@Test
//	public  void  testFindUserByLogin(){
//		String login = "polina";
//		User user = userRepository.findUserByLogin(login);
//
//		Assertions.assertEquals(user.getLogin(),login);
//	}
//
//	@Test
//	public  void  testUpdateUser(){
//		String login = "polina";
//		String password = "polina1";
//		User user = userRepository.findUserByLogin(login);
//		//user.setPassword();
//		//	assertThat(user.getLogin()).isEqualTo(login);
//	}

}
