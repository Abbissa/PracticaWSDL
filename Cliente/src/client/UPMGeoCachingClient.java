package client;

import client.UPMGeoCachingStub.Login;
import client.UPMGeoCachingStub.LoginResponse;
import client.UPMGeoCachingStub.User;



public class UPMGeoCachingClient {
 
  public static void main (String [] args) throws Exception {
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
    
  }
}
