package client;

import client.UPMGeoCachingStub.AddFollower;
import client.UPMGeoCachingStub.AddFollowerResponse;
import client.UPMGeoCachingStub.AddUser;
import client.UPMGeoCachingStub.AddUserResponseE;
import client.UPMGeoCachingStub.ChangePassword;
import client.UPMGeoCachingStub.ChangePasswordResponse;
import client.UPMGeoCachingStub.CreateTreasure;
import client.UPMGeoCachingStub.CreateTreasureResponse;
import client.UPMGeoCachingStub.FindTreasure;
import client.UPMGeoCachingStub.FindTreasureResponse;
import client.UPMGeoCachingStub.FollowerList;
import client.UPMGeoCachingStub.GetMyFollowerTreasuresCreated;
import client.UPMGeoCachingStub.GetMyFollowerTreasuresCreatedResponse;
import client.UPMGeoCachingStub.GetMyFollowers;
import client.UPMGeoCachingStub.GetMyFollowersResponse;
import client.UPMGeoCachingStub.GetMyTreasuresCreated;
import client.UPMGeoCachingStub.GetMyTreasuresCreatedResponse;
import client.UPMGeoCachingStub.GetMyTreasuresFound;
import client.UPMGeoCachingStub.GetMyTreasuresFoundResponse;
import client.UPMGeoCachingStub.Login;
import client.UPMGeoCachingStub.LoginResponse;
import client.UPMGeoCachingStub.Logout;
import client.UPMGeoCachingStub.PasswordPair;
import client.UPMGeoCachingStub.RemoveFollower;
import client.UPMGeoCachingStub.RemoveFollowerResponse;
import client.UPMGeoCachingStub.RemoveUser;
import client.UPMGeoCachingStub.RemoveUserResponse;
import client.UPMGeoCachingStub.Treasure;
import client.UPMGeoCachingStub.User;
import client.UPMGeoCachingStub.Username;



public class UPMGeoCachingClient {

	public static void main (String [] args) throws Exception {
		
		System.out.println("INICIO DE PRUEBAS \n");
		
		String usuario1 = "usuario1";
		String usuario2 = "usuario2";

		// PRUEBA LOGGIN DE ADMIN
		System.out.println("1. LOGIN DE ADMIN");
		UPMGeoCachingStub cs = new UPMGeoCachingStub();
		cs._getServiceClient().engageModule("addressing");
		cs._getServiceClient().getOptions().setManageSession(true);
		System.out.println("\t Stub creado");
		Login login = new Login();
		User user = new User();
		user.setName("admin");
		user.setPwd("admin");
		login.setArgs0(user);
		System.out.println("\t Enviando peticion login");
		LoginResponse lr = cs.login(login);
		System.out.println("\t Login realizado");
		System.out.println("\t Salida: "+ lr.get_return().getResponse() + "\n");
		if (!lr.get_return().getResponse())
			return;

		// BORRAR USUARIO 1
		System.out.println("2. BORRAR USUARIOS QUE SE VAN A UTILIZAR");
		Username username4 = new Username();
		RemoveUser ruser4 = new RemoveUser();
		RemoveUserResponse ruserres4 = new RemoveUserResponse();
		username4.setUsername(usuario1);
		ruser4.setArgs0(username4);
		ruserres4 = cs.removeUser(ruser4);
		System.out.println("\t REMOVEUSER usuario1: resultado = " + ruserres4.get_return().getResponse());

		// BORRAR USUARIO 2
		Username username5 = new Username();
		RemoveUser ruser5 = new RemoveUser();
		RemoveUserResponse ruserres5 = new RemoveUserResponse();
		username5.setUsername(usuario2);
		ruser5.setArgs0(username5);
		ruserres5 = cs.removeUser(ruser5);
		System.out.println("\t REMOVEUSER usuario2: resultado = " + ruserres5.get_return().getResponse() + "\n");

		// PRUEBA ADDUSER
		System.out.println("3. AÑADIR USUARIOS");
		AddUser addUser = new AddUser();
		AddUserResponseE addUserRE = new AddUserResponseE();
		Username newUser = new Username();
		newUser.setUsername(usuario1);
		addUser.setArgs0(newUser);
		addUserRE = cs.addUser(addUser);
		String pwdDev = addUserRE.get_return().getPwd();
		System.out.println("\t Resultado usuario1 = " + addUserRE.get_return().getResponse() + " y pwd = " + pwdDev);

		// PRUEBA ADDUSER 2
		AddUser addUser2 = new AddUser();
		AddUserResponseE addUserRE2 = new AddUserResponseE();
		Username newUser2 = new Username();
		newUser2.setUsername(usuario2);
		addUser2.setArgs0(newUser2);
		addUserRE2 = cs.addUser(addUser2);
		String pwdDev2 = addUserRE2.get_return().getPwd();
		System.out.println("\t Resultado usuario2 = " + addUserRE2.get_return().getResponse() + " y pwd = " + pwdDev2 + "\n");

		// PRUEBA LOGIN DE USUARIO1
		System.out.println("4. LOGIN DE USUARIO1");
		UPMGeoCachingStub cs2 = new UPMGeoCachingStub();
		cs2._getServiceClient().engageModule("addressing");
		cs2._getServiceClient().getOptions().setManageSession(true);
		System.out.println("\t Stub creado");
		Login login2 = new Login();
		User user2 = new User();
		user2.setName(usuario1);
		user2.setPwd(pwdDev);
		login2.setArgs0(user2);
		System.out.println("\t Enviando peticion login");
		LoginResponse lr2 = cs2.login(login2);
		System.out.println("\t Login realizado del usuario " + user2.getName());
		System.out.println("\t Salida: "+ lr2.get_return().getResponse() + "\n");
		
		

		// PRUEBA CHANGEPASSWORD USUARIO 1
		System.out.println("5. CAMBIAR PASSWORD DE USUARIO1");
		PasswordPair pp = new PasswordPair();
		ChangePassword cp = new ChangePassword();
		pp.setNewpwd("nueva");
		pp.setOldpwd(pwdDev);
		cp.setArgs0(pp);
		ChangePasswordResponse cpr = new ChangePasswordResponse();
		cpr = cs2.changePassword(cp);
		System.out.println("\t Resultado = " + cpr.get_return().getResponse() + "\n");
		
		// LOGIN DE USUARIO 1 CON CONTRASEÑA ANTIGUA
		System.out.println("5.1. LOGIN DE USUARIO1 CON CONTRASEÑA ANTIGUA");
		UPMGeoCachingStub cs3 = new UPMGeoCachingStub();
		cs3._getServiceClient().engageModule("addressing");
		cs3._getServiceClient().getOptions().setManageSession(true);
		Login login3 = new Login();
		User user3 = new User();
		user3.setName(usuario1);
		user3.setPwd(pwdDev);
		login3.setArgs0(user3);
		LoginResponse lr3 = cs3.login(login3);
		System.out.println("\t Salida: "+ lr3.get_return().getResponse() + "\n");
		
		// LOGIN DE USUARIO 1 CON NUEVA CONTRASEÑA
		System.out.println("5.2. LOGIN DE USUARIO1 CON CONTRASEÑA NUEVA");
		UPMGeoCachingStub cs4 = new UPMGeoCachingStub();
		cs4._getServiceClient().engageModule("addressing");
		cs4._getServiceClient().getOptions().setManageSession(true);
		Login login4 = new Login();
		User user4 = new User();
		user4.setName(usuario1);
		user4.setPwd("nueva");
		login4.setArgs0(user4);
		LoginResponse lr4 = cs4.login(login4);
		System.out.println("\t Salida: "+ lr4.get_return().getResponse() + "\n");
		
		// CAMBIO DE CONTRASEÑA DE USUARIO 1 DESDE SEGUNDA SESION
		System.out.println("5.3. CAMBIAR PASSWORD DE USUARIO1 DESDE SEGUNDA SESION");
		PasswordPair pp2 = new PasswordPair();
		ChangePassword cp2 = new ChangePassword();
		pp2.setNewpwd("nueva2");
		pp2.setOldpwd("nueva");
		cp2.setArgs0(pp2);
		ChangePasswordResponse cpr2 = new ChangePasswordResponse();
		cpr2 = cs4.changePassword(cp2);
		System.out.println("\t Resultado = " + cpr2.get_return().getResponse() + "\n");
		
		// LOGIN DE USUARIO 1 CON CONTRASEÑA DE PRIMERA SESION
		System.out.println("5.4. LOGIN DE USUARIO1 CON CONTRASEÑA PRIMERA SESION");
		UPMGeoCachingStub cs5 = new UPMGeoCachingStub();
		cs5._getServiceClient().engageModule("addressing");
		cs5._getServiceClient().getOptions().setManageSession(true);
		Login login5 = new Login();
		User user5 = new User();
		user5.setName(usuario1);
		user5.setPwd("nueva");
		login5.setArgs0(user5);
		LoginResponse lr5 = cs5.login(login5);
		System.out.println("\t Salida: "+ lr5.get_return().getResponse() + "\n");
		
		// LOGIN DE USUARIO 1 CON CONTRASEÑA DE SEGUNDA SESION
		System.out.println("5.5. LOGIN DE USUARIO1 CON CONTRASEÑA SEGUNDA SESION");
		UPMGeoCachingStub cs6 = new UPMGeoCachingStub();
		cs6._getServiceClient().engageModule("addressing");
		cs6._getServiceClient().getOptions().setManageSession(true);
		Login login6 = new Login();
		User user6 = new User();
		user6.setName(usuario1);
		user6.setPwd("nueva2");
		login6.setArgs0(user6);
		LoginResponse lr6 = cs6.login(login6);
		System.out.println("\t Salida: "+ lr6.get_return().getResponse() + "\n");
		
		// LOGOUT DE USUARIO 1 SESIONES 2 Y 3
		System.out.println("5.6. LOGOUT DE USUARIO 1 SESIONES 2 Y 3");
		Logout logout10 = new Logout();
		cs4.logout(logout10);
		System.out.println("\t Logout sesion 2 realizado \n");
		
		Logout logout11 = new Logout();
		cs6.logout(logout11);
		System.out.println("\t Logout sesion 3 realizado \n");

		// PRUEBA ADDFOLLOWER A USARIO 1 DE USUARIO 2
		System.out.println("6. ADDFOLLOWER A USUARIO1 DE USUARIO2");
		AddFollower af = new AddFollower();
		Username friend = new Username();
		AddFollowerResponse afr = new AddFollowerResponse();
		friend.setUsername(usuario2);
		af.setArgs0(friend);
		afr = cs2.addFollower(af);
		System.out.println("\t ADDFOLLOWER resultado = " + afr.get_return().getResponse() + "\n");

		// PRUEBA GETMYFOLLOWERS DE USUARIO 1
		System.out.println("7. GETMYFOLLOWERS DE USUARIO 1");
		GetMyFollowers gmf = new GetMyFollowers();
		GetMyFollowersResponse gmflr = new GetMyFollowersResponse();
		//FollowerList fl = new FollowerList();
		gmflr = cs2.getMyFollowers(gmf);
		gmflr.get_return().getFollowers();
		if (gmflr.get_return().getResult()) {
			for (int i = 0; i < gmflr.get_return().getFollowers().length; i++) {
				System.out.println("\t Follower " + i + " = " + gmflr.get_return().getFollowers()[i] + "\n");
			}
		}
		else {
			System.out.println("\t Fallo en GETMYFOLLOWERS \n");
			return;
		}
		
		// CREATETREASURE USUARIO 1
		System.out.println("8. CREATE TREASURE USUARIO 1");
		CreateTreasure createTreasure = new CreateTreasure();
		Treasure tesoro1=new Treasure();
		tesoro1.setName("tesoro");
		tesoro1.setAltitude(34.56);
		tesoro1.setLatitude(-50.25);
		createTreasure.setArgs0(tesoro1);
		CreateTreasureResponse ctresponse = cs2.createTreasure(createTreasure);
		System.out.println("\t Resultado: "+ctresponse.get_return().getResponse() + "\n");
		
		// FIND TREASURE ADMIN
		System.out.println("9. FIND TREASURE DE ADMIN");
		FindTreasure findTreasure=new FindTreasure();
		findTreasure.setArgs0(tesoro1);
		FindTreasureResponse trfresponse = cs.findTreasure(findTreasure);
		System.out.println("\t Resultado: "+trfresponse.get_return().getResponse() + "\n");
		
		// GET MY TREASURES FOUND ADMIN
		System.out.println("10. GET MY TREASURES FOUND DE ADMIN");
		GetMyTreasuresFound getMyTreasuresFound=new GetMyTreasuresFound();
		GetMyTreasuresFoundResponse gmtrf = cs.getMyTreasuresFound(getMyTreasuresFound);
		System.out.println("\t Resultado: " + gmtrf.get_return().getResult());
		if (gmtrf.get_return().getNames().length == 0)
			System.out
					.println("\t No existen tesoros encontrados por el usuario \n");
		else {
			for (int i = 0; i < gmtrf.get_return().getAlts().length; i++) {
				System.out.println("\t Tesoro " + i + " = "
						+ gmtrf.get_return().getAlts()[i] + " ,"
						+ gmtrf.get_return().getLats()[i] + " ,"
						+ gmtrf.get_return().getNames()[i]);
			}
		}
		System.out.println("\n");
		
		// GET MY TREASURES CREATED USUARIO 1
		System.out.println("11. TESOROS CREADOS POR USUARIO1");
		GetMyTreasuresCreated getMyTreasuresCreated=new GetMyTreasuresCreated();
		GetMyTreasuresCreatedResponse gmtrc = cs2.getMyTreasuresCreated(getMyTreasuresCreated);
		System.out.println("\t Resultado: " + gmtrc.get_return().getResult());
		if (gmtrc.get_return().getResult()) {
			if (gmtrc.get_return().getAlts().length > 0) {
				for (int i = 0; i < gmtrc.get_return().getAlts().length; i++) {
					System.out.println("\t Tesoro " + i + " = "
							+ gmtrc.get_return().getAlts()[i] + " ,"
							+ gmtrc.get_return().getLats()[i] + " ,"
							+ gmtrc.get_return().getNames()[i]);
				}
			}
		}
		System.out.println("\n");
		
		// ADDFOLLOWER A ADMIN DE USUARIO1
		System.out.println("12. AÑADIR FOLLOWER USUARIO1 A ADMIN");
		AddFollower af2 = new AddFollower();
		Username friend2 = new Username();
		AddFollowerResponse afr2 = new AddFollowerResponse();
		friend2.setUsername(usuario2);
		af2.setArgs0(friend2);
		afr2 = cs.addFollower(af2);
		System.out.println("\t ADDFOLLOWER resultado = " + afr2.get_return().getResponse() + "\n");
		
		// GET MY FOLLOWER TREASURES CREATED
		System.out.println("13. LISTA DE TESOROS CREADOS POR FOLLOWER DE ADMIN (USUARIO1)");
		Username uuu = new Username();
		uuu.setUsername(usuario2);
		GetMyFollowerTreasuresCreated getMyFollowerCreated = new GetMyFollowerTreasuresCreated();
		getMyFollowerCreated.setArgs0(uuu);
		GetMyFollowerTreasuresCreatedResponse gmftrc = cs
				.getMyFollowerTreasuresCreated(getMyFollowerCreated);
		System.out.println("\t GETMYFOLLOWERTREASURECREATED resultado = "
				+ gmftrc.get_return().getResult());
		if (gmftrc.get_return().getResult()) {
			if (gmftrc.get_return().getAlts() != null) {
				if (gmftrc.get_return().getResult()) {
					for (int i = 0; i < gmftrc.get_return().getAlts().length; i++) {
						System.out.println("\t Tesoro " + i + " = "
								+ gmftrc.get_return().getAlts()[i] + " ,"
								+ gmftrc.get_return().getLats()[i] + " ,"
								+ gmftrc.get_return().getNames()[i]);
					}
				}
			} else
				System.out.println("\t El usuario " + usuario2
						+ " no tiene tesoros creados");
		}
		System.out.println("\n");
		
		
		
		
		
		
		createTreasure.getArgs0().setLatitude(23);
		cs2.createTreasure(createTreasure);
		
		tesoro1.setName("tesoro2");
		CreateTreasure createTreasure2 = new CreateTreasure();
		createTreasure2.setArgs0(tesoro1);
		cs.createTreasure(createTreasure2);
		
		
		
		
		
		

		// REMOVEFOLLOWER A USUARIO 1 DE USUARIO 2
		System.out.println("14. ELIMINAR USUARIO 2 DE FOLLOWERS DE USUARIO 1");
		RemoveFollower rf = new RemoveFollower();
		RemoveFollowerResponse rfr = new RemoveFollowerResponse();
		Username fr = new Username();
		fr.setUsername(usuario2);
		rf.setArgs0(fr);
		rfr = cs2.removeFollower(rf);
		System.out.println("\t Resultado = " + rfr.get_return().getResponse() + "\n");

		// GETMYFOLLOWERS DE USUARIO 1
		System.out.println("15. LISTAR FOLLOWERS DE USUARIO 1");
		gmflr = cs2.getMyFollowers(gmf);
		gmflr.get_return().getFollowers();
		if (gmflr.get_return().getResult()&&gmflr.get_return().getFollowers()!=null) {
			for (int i = 0; i < gmflr.get_return().getFollowers().length; i++) {
				System.out.println("\t Amigo " + i + " = " + gmflr.get_return().getFollowers()[i]);
			}
		}
		else
			System.out.println("\t Resultado = " + gmflr.get_return().getResult() + "\n");
		
				

		// REMOVEUSER DE USUARIO 1 DESDE USUARIO 1
		System.out.println("16. USUARIO 1 ELIMINA USUARIO 1");
		Username username2 = new Username();
		RemoveUser ruser2 = new RemoveUser();
		RemoveUserResponse ruserres2 = new RemoveUserResponse();
		username2.setUsername(usuario1);
		ruser2.setArgs0(username2);
		ruserres2 = cs2.removeUser(ruser2);
		System.out.println("\t Resultado = " + ruserres2.get_return().getResponse() + "\n");

		// PRUEBA REMOVEUSER DE USUARIO 1 DESDE ADMIN
		System.out.println("17. ADMIN ELIMINA A USUARIO 1 ELIMINADO PREVIAMENTE");
		Username username = new Username();
		RemoveUser ruser = new RemoveUser();
		RemoveUserResponse ruserres = new RemoveUserResponse();
		username.setUsername(usuario1);
		ruser.setArgs0(username);
		ruserres = cs.removeUser(ruser);
		System.out.println("\t REMOVEUSER: resultado = " + ruserres.get_return().getResponse() + "\n");

		// REMOVEUSER DE USUARIO 2 DESDE ADMIN
		System.out.println("18. ADMIN ELIMINA A USUARIO 2");
		Username username3 = new Username();
		RemoveUser ruser3 = new RemoveUser();
		RemoveUserResponse ruserres3 = new RemoveUserResponse();
		username3.setUsername(usuario2);
		ruser3.setArgs0(username3);
		ruserres3 = cs.removeUser(ruser3);
		System.out.println("\t REMOVEUSER: resultado = " + ruserres3.get_return().getResponse() + "\n");

		// PRUEBA LOGOUT DE ADMIN
		System.out.println("19. LOGOUT DE ADMIN");
		Logout logout = new Logout();
		cs.logout(logout);
		System.out.println("\t Logout realizado \n");

		// LOGOUT DE USUARIO 1
		System.out.println("20. LOGOUT DE USUARIO 1");
		Logout logout2 = new Logout();
		cs2.logout(logout2);
		System.out.println("\t Logout realizado \n");

	}
}
