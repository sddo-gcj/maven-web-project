package first.webapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;

import first.webapp.UserServlet;
import com.mysql.cj.protocol.Resultset;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServletTest {
  
	  private UserServlet servlet; 
	  private HttpServletRequest request; 
	  private HttpServletResponse response; 
	  private Connection mockConnection; 
	  private PreparedStatement mockPreparedStatement;
	  private MockedStatic<DriverManager> mockedDriverManager;
	  
	  private ResultSet mockResultSet;
	  private Statement mockStatement;
  
  @BeforeEach 
  public void setUp() { 
	  servlet = new UserServlet(); 
	  request = mock(HttpServletRequest.class); 
	  response = mock(HttpServletResponse.class);
      mockConnection = mock(Connection.class); 
      mockPreparedStatement = mock(PreparedStatement.class);
      mockedDriverManager = mockStatic(DriverManager.class);
      mockResultSet = mock(ResultSet.class);
      mockStatement = mock(Statement.class);
  }
  
	@AfterEach
	void tearDown() throws Exception {
		if (mockedDriverManager != null) {
            mockedDriverManager.close();
		}
		if (mockResultSet != null) {
		   mockResultSet.close();
		}	
	}
  
@Test 
  void testDeleteUserSuccess() throws Exception {
	  
	 when(request.getParameter("name")).thenReturn("eric");
	 
     when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
     when(mockPreparedStatement.executeUpdate()).thenReturn(1);

    // MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class); 
     mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
     
     assertTrue(servlet.deleteUser(request,response));
     
     verify(mockPreparedStatement).setString(1, "eric"); 
      }

//**********************************************************************************************************************************************
//**********************************************************************************************************************************************  
 @Test 
 void testUpdateUserSuccess() throws Exception {
  
	  when(request.getParameter("oriName")).thenReturn("eric");
	  when(request.getParameter("name")).thenReturn("eric_Test");
	  when(request.getParameter("password")).thenReturn("password_Test");
	  when(request.getParameter("email")).thenReturn("eric_Test@gmail.com");
	  when(request.getParameter("language")).thenReturn("Chinese");
  
	  StringWriter stringWriter = new StringWriter(); 
	  PrintWriter writer = new PrintWriter(stringWriter); 
	  when(response.getWriter()).thenReturn(writer);
  
	  MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class); 
	  mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
  
	  when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
	  when(mockPreparedStatement.executeUpdate()).thenReturn(1);
  
	  servlet.doPost(request, response);
  
	  writer.flush(); 
	  String output = stringWriter.toString();
	  assertTrue(output.contains("User Updated Successfully"));
  
	  verify(mockPreparedStatement).setString(1, "eric");
	  verify(mockPreparedStatement).setString(2, "eric_Test");
	  verify(mockPreparedStatement).setString(3, "password_Test");
	  verify(mockPreparedStatement).setString(4, "eric_Test@gmail.com");
	  verify(mockPreparedStatement).setString(5, "Chinese"); 
 }

//**********************************************************************************************************************************************
//**********************************************************************************************************************************************  
 
@Test 
void testListUserSuccess() throws Exception { 
	  
	  StringWriter stringWriter = new StringWriter(); 
	  PrintWriter writer = new PrintWriter(stringWriter);
	  when(response.getWriter()).thenReturn(writer);
  
	  MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class); 
	  mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
  
	  when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
	  when(mockPreparedStatement.executeUpdate()).thenReturn(1);
  
	  servlet.doPost(request, response);

	  writer.flush(); 
	  String output = stringWriter.toString();
	  assertTrue(output.contains("All Users Listed Successfully")); 
 }


//**********************************************************************************************************************************************
//**********************************************************************************************************************************************
  
 @Test 
 void testCreateUserSuccess() throws Exception {
  
	  when(request.getParameter("userName")).thenReturn("eric");
	  when(request.getParameter("password")).thenReturn("password123");
	  when(request.getParameter("email")).thenReturn("eric@gmail.com");
	  when(request.getParameter("language")).thenReturn("English");
  
	  StringWriter stringWriter = new StringWriter(); 
	  PrintWriter writer = new PrintWriter(stringWriter); 
	  when(response.getWriter()).thenReturn(writer);
  
	  MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class); 
	  mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
	  when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
	  when(mockPreparedStatement.executeUpdate()).thenReturn(1);
  
	  servlet.doPost(request, response);
  
	  writer.flush(); 
	  String output = stringWriter.toString();
	  assertTrue(output.contains("You are successfully registered"));

	  verify(mockPreparedStatement).setString(1, "eric");
	  verify(mockPreparedStatement).setString(2, "password123");
	  verify(mockPreparedStatement).setString(3, "eric@gmail.com");
	  verify(mockPreparedStatement).setString(4, "English"); 
  }
}

 