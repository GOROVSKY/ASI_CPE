package com.sp.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sp.entity.Users;
import com.sp.model.UserRepository;
import com.sp.service.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UsersService userServiceMock;

	@Mock
	UserRepository userRepository;
	

	@Test
	public void testCreateUserWithWallet5000() {
		String password = "tata";
		Users user = new Users("toto", "titi", password);
		userServiceMock.addUser(user);
		
		//par défaut wallet de 5000
		assertEquals(5000, user.getWallet());
	}
	
}
