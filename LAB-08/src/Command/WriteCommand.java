package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Dao;
import Dto.Dto;

public class WriteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		Dao dao = new Dao();
		dao.write(bName, bTitle, bContent);
		
	}

}
