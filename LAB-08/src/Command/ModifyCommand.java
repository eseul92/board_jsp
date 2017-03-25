package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Dao;

public class ModifyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		Dao dao = new Dao();
		dao.modify(bId, bName, bTitle, bContent);
		
	}

}
