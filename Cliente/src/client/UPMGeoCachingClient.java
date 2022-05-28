package client;

import client.UPMGeoCachingStub.AddFollower;
import client.UPMGeoCachingStub.AddFollowerResponse;
import client.UPMGeoCachingStub.AddUser;
import client.UPMGeoCachingStub.AddUserResponseE;
import client.UPMGeoCachingStub.ChangePassword;
import client.UPMGeoCachingStub.ChangePasswordResponse;
import client.UPMGeoCachingStub.FollowerList;
import client.UPMGeoCachingStub.GetMyFollowers;
import client.UPMGeoCachingStub.GetMyFollowersResponse;
import client.UPMGeoCachingStub.Login;
import client.UPMGeoCachingStub.LoginResponse;
import client.UPMGeoCachingStub.Logout;
import client.UPMGeoCachingStub.PasswordPair;
import client.UPMGeoCachingStub.RemoveFollower;
import client.UPMGeoCachingStub.RemoveFollowerResponse;
import client.UPMGeoCachingStub.RemoveUser;
import client.UPMGeoCachingStub.RemoveUserResponse;
import client.UPMGeoCachingStub.User;
import client.UPMGeoCachingStub.Username;



public class UPMGeoCachingClient {
 
  public static void main (String [] args) throws Exception {
	  
	  // PRUEBA LOGGIN DE ADMIN
	  System.out.println("Inicio");
	  UPMGeoCachingStub cs = new UPMGeoCachingStub();
    cs._getServiceClient().engageModule("addressing");
    cs._getServiceClient().getOptions().setManageSession(true);
    System.out.println("Stub creado");
    Login login = new Login();
    User user = new User();
    user.setName("admin");
    user.setPwd("admin");
    login.setArgs0(user);
    System.out.println("Enviando peticion login");
    LoginResponse lr = cs.login(login);
    System.out.println("Login realizado");
    System.out.println("Salida: "+ lr.get_return().getResponse());
    
    // BORRAR USUARIO PACO
    Username username4 = new Username();
    RemoveUser ruser4 = new RemoveUser();
    RemoveUserResponse ruserres4 = new RemoveUserResponse();
    username4.setUsername("paco15");
    ruser4.setArgs0(username4);
    ruserres4 = cs.removeUser(ruser4);
    System.out.println("REMOVEUSER: resultado = " + ruserres4.get_return().getResponse());
    
    // BORRAR USER ALICIA
    Username username5 = new Username();
    RemoveUser ruser5 = new RemoveUser();
    RemoveUserResponse ruserres5 = new RemoveUserResponse();
    username5.setUsername("alicia34");
    ruser5.setArgs0(username5);
    ruserres5 = cs.removeUser(ruser5);
    System.out.println("REMOVEUSER: resultado = " + ruserres5.get_return().getResponse());
    
    // PRUEBA ADDUSER
    AddUser addUser = new AddUser();
    AddUserResponseE addUserRE = new AddUserResponseE();
    Username newUser = new Username();
    newUser.setUsername("paco15");
    addUser.setArgs0(newUser);
    addUserRE = cs.addUser(addUser);
    String pwdDev = addUserRE.get_return().getPwd();
    System.out.println("Resultado = " + addUserRE.get_return().getResponse() + " y pwd = " + pwdDev);
    
    // PRUEBA ADDUSER 2
    AddUser addUser2 = new AddUser();
    AddUserResponseE addUserRE2 = new AddUserResponseE();
    Username newUser2 = new Username();
    newUser2.setUsername("alicia34");
    addUser2.setArgs0(newUser2);
    addUserRE2 = cs.addUser(addUser2);
    String pwdDev2 = addUserRE2.get_return().getPwd();
    System.out.println("Resultado = " + addUserRE2.get_return().getResponse() + " y pwd = " + pwdDev2);
    
    // PRUEBA LOGIN DE USUARIO ANTERIOR
    UPMGeoCachingStub cs2 = new UPMGeoCachingStub();
    cs2._getServiceClient().engageModule("addressing");
    cs2._getServiceClient().getOptions().setManageSession(true);
    System.out.println("Stub creado");
    Login login2 = new Login();
    User user2 = new User();
    user2.setName("paco15");
    user2.setPwd(pwdDev);
    login2.setArgs0(user2);
    System.out.println("Enviando peticion login");
    LoginResponse lr2 = cs2.login(login2);
    System.out.println("Login realizado del usuario " + user2.getName());
    System.out.println("Salida: "+ lr2.get_return().getResponse());
    
    // PRUEBA CHANGEPASSWORD PACO
    PasswordPair pp = new PasswordPair();
    ChangePassword cp = new ChangePassword();
    pp.setNewpwd("nueva");
    pp.setOldpwd(pwdDev);
    cp.setArgs0(pp);
    ChangePasswordResponse cpr = new ChangePasswordResponse();
    cpr = cs2.changePassword(cp);
    System.out.println("CHANGEPASSWORD PACO");
    System.out.println("\t" + cpr.get_return().getResponse());
    
    // PRUEBA ADDFOLLOWER A PACO
    AddFollower af = new AddFollower();
    Username friend = new Username();
    AddFollowerResponse afr = new AddFollowerResponse();
    friend.setUsername("alicia34");
    af.setArgs0(friend);
    afr = cs2.addFollower(af);
    System.out.println("ADDFOLLOWER resultado = " + afr.get_return().getResponse());
    
    // PRUEBA GETMYFOLLOWERS PACO
    System.out.println("GETMYFOLLOWERS PACO");
    GetMyFollowers gmf = new GetMyFollowers();
    GetMyFollowersResponse gmflr = new GetMyFollowersResponse();
    //FollowerList fl = new FollowerList();
    gmflr = cs2.getMyFollowers(gmf);
    gmflr.get_return().getFollowers();
    if (gmflr.get_return().getResult()) {
    	for (int i = 0; i < gmflr.get_return().getFollowers().length; i++) {
    		System.out.println("\t Amigo " + i + " = " + gmflr.get_return().getFollowers()[i]);
    	}
    }
    else
    	System.out.println("\t Fallo en GETMYFOLLOWERS");
    
    // PRUEBA REMOVEFOLLOWER
    RemoveFollower rf = new RemoveFollower();
    RemoveFollowerResponse rfr = new RemoveFollowerResponse();
    Username fr = new Username();
    fr.setUsername("alicia34");
    rf.setArgs0(fr);
    rfr = cs2.removeFollower(rf);
    System.out.println("REMOVEFOLLOWER");
    System.out.println("\t resultado = " + rfr.get_return().getResponse());
    
    System.out.println("GETMYFOLLOWERS PACO");
    gmflr = cs2.getMyFollowers(gmf);
    gmflr.get_return().getFollowers();
    if (gmflr.get_return().getResult()) {
    	for (int i = 0; i < gmflr.get_return().getFollowers().length; i++) {
    		System.out.println("\t Amigo " + i + " = " + gmflr.get_return().getFollowers()[i]);
    	}
    }
    else
    	System.out.println("\t Fallo en GETMYFOLLOWERS");
    
    // PRUEBA REMOVEUSER DE USUARIO PACO CON USUARIO PACO
    Username username2 = new Username();
    RemoveUser ruser2 = new RemoveUser();
    RemoveUserResponse ruserres2 = new RemoveUserResponse();
    username2.setUsername("paco15");
    ruser2.setArgs0(username2);
    ruserres2 = cs2.removeUser(ruser2);
    System.out.println("REMOVEUSER PACO: resultado = " + ruserres2.get_return().getResponse());
    
    // PRUEBA REMOVEUSER CON USUARIO = ADMIN
    Username username = new Username();
    RemoveUser ruser = new RemoveUser();
    RemoveUserResponse ruserres = new RemoveUserResponse();
    username.setUsername("paco15");
    ruser.setArgs0(username);
    ruserres = cs.removeUser(ruser);
    System.out.println("REMOVEUSER: resultado = " + ruserres.get_return().getResponse());
    
    // BORRAR USER ALICIA
    Username username3 = new Username();
    RemoveUser ruser3 = new RemoveUser();
    RemoveUserResponse ruserres3 = new RemoveUserResponse();
    username3.setUsername("alicia34");
    ruser3.setArgs0(username3);
    ruserres3 = cs.removeUser(ruser3);
    System.out.println("REMOVEUSER: resultado = " + ruserres3.get_return().getResponse());
    
    // PRUEBA LOGOUT DE ADMIN
    System.out.println("logout");
    Logout logout = new Logout();
    cs.logout(logout);
    System.out.println("logout realizado");
    
    // LOGOUT DE PACO
    System.out.println("logout");
    Logout logout2 = new Logout();
    cs2.logout(logout2);
    System.out.println("logout realizado");
    
  }
}
