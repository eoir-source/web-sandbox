package webApp;

import javax.jws.WebService;

@WebService()
public interface AccountRetrieval
{
	public Info getInfo(String accountUSER);
	// .....
}
