
package request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
	      System.out.println("账号是"+name1+"密码是"+password1);
	      try {
			Class.forName("com.mysql.jdbc.Driver");
			//1.建立连接对象
			try {
				Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","123456");
				String sql="insert into user(name,password) values(?,?)";
				PreparedStatement pst=(PreparedStatement)con.prepareStatement(sql);
				pst.setObject(1,name1);//取代第一个问好
				pst.setObject(2, password1);//取代第二个问号
				int jieguo=pst.executeUpdate();//执行sql语句，并将执行结果赋值给jieguo
				JSONArray jsonarray=new JSONArray();//将json数据封装成一个数组
				JSONObject jsonobject=new JSONObject();//读取一行json数据
				//通过字段检索
				jsonobject.put("jieguo",jieguo);
				jsonarray.add(jsonobject);
				out=response.getWriter();
				out.println(jsonarray);
				pst.close();
				con.close();
			} catch (SQLException e) {
				out.println("get data error");
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
