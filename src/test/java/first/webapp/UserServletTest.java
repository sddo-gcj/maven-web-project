package first.webapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServletTest {
	
	private RegisterServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection mockConnection;
	private PreparedStatement mockPreparedStatement;
	
	@BeforeEach
	public void setUp() {
           servlet = new RegisterServlet();
           request = mock(HttpServletRequest.class);
           response = mock(HttpServletResponse.class);
           mockConnection = mock(Connection.class);
           mockPreparedStatement = mock(PreparedStatement.class);
	}
	
	@Test
	void testDeleteUserSuccess() throws Exception {
	    when(request.getParameter("name")).thenReturn("eric");
		
		servlet.doPost(request, response);
		
		MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class);
		mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
	    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
	    when(mockPreparedStatement.executeUpdate()).thenReturn(1);
	
	}
	
}