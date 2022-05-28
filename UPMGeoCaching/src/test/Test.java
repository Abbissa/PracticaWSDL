package test;

import es.upm.fi.sos.AddUser;
import es.upm.fi.sos.AddUserResponse;
import es.upm.fi.sos.Login;
import es.upm.fi.sos.LoginResponse;
import es.upm.fi.sos.RemoveUser;
import es.upm.fi.sos.RemoveUserResponse;
import es.upm.fi.sos.UPMGeoCachingSkeleton;
import es.upm.fi.sos.model.xsd.User;
import es.upm.fi.sos.model.xsd.Username;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UPMGeoCachingSkeleton test = new UPMGeoCachingSkeleton();

		Login login = new Login();
		User user = new User();
		user.setName("admin");
		user.setPwd("admin");
		login.setArgs0(user);

		LoginResponse res = test.login(login);

		System.out.println(res.get_return().getResponse());

		System.out.println("ADDUSER");
		AddUser addUser= new AddUser();
		Username username = new Username();
		username.setUsername("3456abcde54567");
		addUser.setArgs0(username);
		AddUserResponse a = test.addUser(addUser);
		System.out.println(a.get_return().getResponse());
		System.out.println(a.get_return().getPwd());
		System.out.println("REMOVEUSER");
		RemoveUser removeUser=new RemoveUser();
		Username param= new Username();
		param.setUsername("3456abcde54567");
		removeUser.setArgs0(param);
		RemoveUserResponse b = test.removeUser(removeUser);
		
		System.out.println(b.get_return().getResponse());

	}

}
