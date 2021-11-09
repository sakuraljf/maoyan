
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
@WebServlet("/Ding")
public class Ding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ding() {
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
	      String username1 = request.getParameter("username");
	      String filmName1=request.getParameter("filmName");
	      int filmId1=0;
	      try {
	       filmId1=Integer.parseInt(request.getParameter("filmId")); 
	      }catch(NumberFormatException e) {
	    	  System.out.println(e);
	      }
	      String time1=request.getParameter("time");
	      String yuanName1=request.getParameter("yuanName");
	      String locations1=request.getParameter("locations");
	      System.out.println(username1+filmName1+filmId1+time1+yuanName1+locations1);
	      try {
			Class.forName("com.mysql.jdbc.Driver");
			//1.建立连接对象
			try {
				Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","123456");
				String sql="insert into Dingdan values(?,?,?,?,?,?,null)";
				PreparedStatement pst=(PreparedStatement)con.prepareStatement(sql);
				pst.setObject(1,username1);//取代第一个问好
				pst.setObject(2,filmName1);//取代第二个问号
				pst.setObject(3,filmId1);
				pst.setObject(4,time1);
				pst.setObject(5,yuanName1);
				pst.setObject(6,locations1);
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
