
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class list
 */

@WebServlet("/listcha")
public class listcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listcha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json; charset=utf-8");
		 PrintWriter out = response.getWriter();
		

		   
		          try { 
		        	  Class.forName("com.mysql.jdbc.Driver");
		        		
		        		  Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chayan","root","123456");
		
		                   Statement stmt = con.createStatement(); //创建Statement对象
		                    String sql;
	                   sql = "select * from xq";
		                    ResultSet rs = stmt.executeQuery(sql);
		                    JSONArray jsonarray = new JSONArray();  
		                    JSONObject jsonobj = new JSONObject(); 
		                    // 展开结果集数据库
		                    while(rs.next()){
		                        // 通过字段检索
		                        jsonobj.put("id", rs.getString("id"));    //这个是返回的内容1
	                            jsonobj.put("name", rs.getString("name"));    //这个是返回的内容2
		                        jsonobj.put("yueshou", rs.getString("yueshou"));           //这个是返回的内容3                 
		                        jsonobj.put("price", rs.getString("price"));    //这个是返回的内容4
		                        jsonobj.put("img", rs.getString("img"));
		                        jsonobj.put("miaoshu", rs.getString("miaoshu"));
		                        jsonarray.add(jsonobj);             
		                     }
		                    // 输出数据
		                   out = response.getWriter();
		                   
		                   out.println(jsonarray);
		                   // 完成后关闭
		                   rs.close();   
		                    stmt.close();   
		                    con.close();                  
		                }catch (Exception e) { 
		                       out.print("get data error!"); 
		                      e.printStackTrace(); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
