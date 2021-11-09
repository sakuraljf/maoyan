import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jdbc1 {
public static void main(String[] args) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chayan","root","123456");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from xq");
		while(rs.next())
		{
			System.out.println(rs.getString("name")+" "+rs.getString("price"));
		}
		rs.close();
		st.close();
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
