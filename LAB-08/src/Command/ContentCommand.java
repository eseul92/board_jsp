package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Dao;
import Dto.Dto;

public class ContentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bId = request.getParameter("bId");
		Dao dao = new Dao();
		Dto dto = dao.contentView(bId);
		
		request.setAttribute("content_view", dto);
	}
	
}
