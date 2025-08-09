package first.webapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	
	private User user;
	private User user2;
	private User user3;	
	
	@BeforeEach
	void setUp() throws Exception {
		user = new User("eric", "password", "eric@gmail.com", "English");
	}

	@AfterEach
	void tearDown() throws Exception {
		user=null;
		user2=null;
		user3=null;
	}

	@Test
	void testHashCode() {
		user = new User("eric", "password", "eric@gmail.com", "English");
		user2 = new User("eric", "password", "eric@gmail.com", "English");
		user3 = new User("erictan", "password123", "erictan@gmail.com", "Spanish");
		
		int user_1_hash = user.hashCode();
		int user_2_hash = user2.hashCode();
		int user_3_hash = user3.hashCode();
		
		assertEquals(user_1_hash,user_2_hash);
		assertFalse(user_1_hash == user_3_hash);
	}
	
	@Test
	void testUser() {
		user2 = new User("erictan1", "password321", "erictan1@gmail.com", "English");
		assertEquals(user2.getName(),"erictan1");
	}
	
	@Test
	void testGetName() {
		assertEquals(user.getName(),"eric");
	}	
	
	@Test
	void testSetName() {
		user.setName("eric555");
		assertEquals(user.getName(),"eric555");
	}	
	
	@Test
	void testGetPassword() {
		assertEquals(user.getPassword(),"password");
	}	
	
	@Test
	void testSetPassword() {
		user.setPassword("passwordDefault");
		assertEquals(user.getPassword(),"passwordDefault");		
	}	
	
	@Test
	void testGetEmail() {
		assertEquals(user.getEmail(),"eric@gmail.com");		
	}	
	
	@Test
	void testSetEmail() {
		user.setEmail("erictanx@gmail.com");
		assertEquals(user.getEmail(),"erictanx@gmail.com");		
	}	
	
	@Test
	void testGetLanguage() {
		assertEquals(user.getLanguage(),"English");
	}	

	@Test
	void testSetLanguage() {
		user.setLanguage("Mandarin");
		assertEquals(user.getLanguage(),"Mandarin");
	}
	
	@Test
	void testEqualsObject() {
		User user3 = new User("Tan3","password","tan3@email.com","English");
		User user4 = new User("Tan4","password","tan4@email.com","Spanish");
		
		assertTrue(user.equals(user));
		assertFalse(user.equals("NoName"));	
		assertTrue(user.equals(user3));
		assertFalse(user.equals(user4));
	}
}
