package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Dao;

public class ReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		System.out.println("bGroup: " + bGroup);
		
		Dao dao = new Dao();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}

}
