
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
@WebServlet("/ping")
public class ping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ping() {
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
	      String username = request.getParameter("username");
	      String time=request.getParameter("time");
	      int filmId=0;
	      try {
	       filmId=Integer.parseInt(request.getParameter("filmId")); 
	      }catch(NumberFormatException e) {
	    	  System.out.println(e);
	      }
	      
	      int grade=0;
	      try {
	       grade=Integer.parseInt(request.getParameter("grade")); 
	      }catch(NumberFormatException e) {
	    	  System.out.println(e);
	      }
	      String content=request.getParameter("content");
	      System.out.println(username+time+grade+content+filmId);
	      try {
			Class.forName("com.mysql.jdbc.Driver");
			//1.建立连接对象
			try {
				Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","123456");
				String sql="insert into ping values(?,?,?,?,?,null)";
				PreparedStatement pst=(PreparedStatement)con.prepareStatement(sql);
				pst.setObject(1,username);//取代第一个问好
				pst.setObject(2,time);//取代第二个问号
				pst.setObject(3,grade);
				pst.setObject(4,content);
				pst.setObject(5,filmId);
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
