package request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;
/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		 PrintWriter out = response.getWriter();
	     response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	      String name1 = request.getParameter("name");
	      String password1=request.getParameter("password");
	      System.out.println("登录账号是"+name1+"密码是"+password1);
	      
	      try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","123456");
				Statement stmt=con.createStatement();
				String sql="select * from user where name='"+name1+"'"+"and password="+"'"+password1+"'";
				 System.out.println(sql);
				ResultSet rs=stmt.executeQuery(sql);
				JSONArray jsonarray=new JSONArray();//将json数据封装成一个数组
				JSONObject jsonobject=new JSONObject();//读取一行json数据
				while(rs.next())
				{
					jsonobject.put("jieguo",1);
					jsonarray.add(jsonobject);
					
				}
				out=response.getWriter();
				out.println(jsonarray);
				stmt.close();
				con.close();
			} catch (SQLException e) {
				out.print("data get err");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
