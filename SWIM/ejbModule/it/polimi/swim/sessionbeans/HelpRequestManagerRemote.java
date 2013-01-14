package it.polimi.swim.sessionbeans;

import it.polimi.swim.entities.HelpRequest;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface HelpRequestManagerRemote {
	
	public Integer createHelpRequest(String title, String city, String description, Date date, Time hour, int IDUser, int IDAbility);
	
	public Integer replyToHelpRequest(int IDUser, int IDHelpRequest);
	
	public boolean checkCanReplyToHelpRequest(int IDUser, int IDHelpRequest);
	
	public boolean markAsBestReply(int IDReply);
	
	public boolean hasBestReply(int IDHelpRequest);
	
	public Integer createFeedback(int mark, String description, int IDUser, int IDAbility, int IDReply);
	
	public boolean hasFeedback(int IDHelpRequest);
	
	public List<HelpRequest> getHelpRequests();
	
	public HelpRequest getHelpRequest(int IDHr);
	
	public boolean postedByMe(int IDUser, int IDHr);
	
	public HelpRequest getHelpRequestFromReply(int IDReply);
}
