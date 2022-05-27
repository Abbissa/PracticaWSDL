package client;

import client.UPMGeoCachingStub.Login;
import client.UPMGeoCachingStub.LoginResponse;
import client.UPMGeoCachingStub.User;



public class UPMGeoCachingClient {
 
  public static void main (String [] args) throws Exception {
   UPMGeoCachingStub cs = new UPMGeoCachingStub();
    cs._getServiceClient().getOptions().setManageSession(true);
    cs._getServiceClient().engageModule("addressing");
    Login login = new Login();
    User user = new User();
    user.setName("admin");
    user.setPwd("admin");
    login.setArgs0(user);
    LoginResponse lr = cs.login(login);
  }
}
