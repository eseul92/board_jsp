package FrontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import Command.ContentCommand;
import Command.DeleteCommand;
import Command.ListCommand;
import Command.ModifyCommand;
import Command.ReplyCommand;
import Command.ReplyViewCommand;
import Command.WriteCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("actionDo");
		
		request.setCharacterEncoding("EUC-KR");
		
		String viewPage = null;
		Command command = null;
		
		//URI 정보 출력
		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);
		
		//경로 정보 출력
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		
		String[] arrURI = uri.split("/");
		String comm = arrURI[arrURI.length-1];
		
		System.out.println("arrURI: " + arrURI.length);
		
		for(int i=0; i<arrURI.length; i++){
			System.out.format("arrURI[%d] : %s\n", i, arrURI[i]);
		}
		
		System.out.println("comm : " + comm);
		
		if(comm.equals("list.do")){
			command = new ListCommand();
			command.execute(request, response);
			viewPage="list.jsp";
		}else if(comm.equals("write_view.do")){
			viewPage="write_view.jsp";
		}else if(comm.equals("write.do")){
			command = new WriteCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("content_view.do")){
			command = new ContentCommand();
			command.execute(request, response);
			viewPage="content_view.jsp";
		}else if(comm.equals("modify.do")){
			command = new ModifyCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("delete.do")){
			command = new DeleteCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("reply_view.do")){
			command = new ReplyViewCommand();
			command.execute(request, response);
			viewPage="reply_view.jsp";
		}else if(comm.equals("reply.do")){
			command = new ReplyCommand();
			command.execute(request, response);
			viewPage="list.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
