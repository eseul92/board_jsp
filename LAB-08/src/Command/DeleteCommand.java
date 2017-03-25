package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Dao;

public class DeleteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bId = request.getParameter("bId");
		
		Dao dao = new Dao();
		dao.delete(bId);
	}

}
