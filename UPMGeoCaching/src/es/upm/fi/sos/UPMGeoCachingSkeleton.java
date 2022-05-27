/**
 * UPMGeoCachingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.upm.fi.sos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.axis2.AxisFault;

import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponseE;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.Login;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserResponseE;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE;
import es.upm.fi.sos.model.xsd.FollowerList;
import es.upm.fi.sos.model.xsd.PasswordPair;
import es.upm.fi.sos.model.xsd.Response;
import es.upm.fi.sos.model.xsd.TreasureList;
import es.upm.fi.sos.model.xsd.Username;

/**
 * UPMGeoCachingSkeleton java skeleton for the axisService
 */
public class UPMGeoCachingSkeleton {

	private boolean logged;
	private String username;
	private String password;
	static String admin = "admin";
	static String adminPWD = "admin";
	private int nsesiones = 0;
	UPMAuthenticationAuthorizationWSSkeletonStub cs;

	private static HashMap<String, User> users;
	private static HashMap<String, Tesoro> treasures;

	public UPMGeoCachingSkeleton() {

		logged = false;
		username = null;
		password = null;
		/*if (admin != null)
			admin = "admin";
		if (adminPWD != null)
			adminPWD = "admin";
		if (users != null)
			users = new HashMap<String, User>();*/
		if(users.equals(null))
			users = new HashMap<String, User>();
		if(treasures.equals(null))
			treasures = new HashMap<String, Tesoro>();
		if (!users.containsKey(admin))
			users.put(admin, new User(admin, adminPWD));
		try {
			cs = new UPMAuthenticationAuthorizationWSSkeletonStub();
			cs._getServiceClient().getOptions().setManageSession(true);
			cs._getServiceClient().engageModule("addressing");
		} catch (AxisFault e) {
			System.out.println("Error de inicio de stub.\n");
			e.printStackTrace();
		}

	}

	/**
	 * Auto generated method signature
	 * 
	 * @param logout
	 * @return
	 */
	public void logout(es.upm.fi.sos.Logout logout) {
		if(username != null && users.containsKey(username)) {
			// Decrementar numero de sesiones de username
			nsesiones--;
			// Si el numero de sesiones es 0, poner username a null
			if (nsesiones == 0) {
				username = null;
				logged = false;
			}
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeFollower
	 * @return removeFollowerResponse
	 */
	public es.upm.fi.sos.RemoveFollowerResponse removeFollower(
			es.upm.fi.sos.RemoveFollower removeFollower) {

		RemoveFollowerResponse response = new RemoveFollowerResponse();
		if (!logged) {
			response.get_return().setResponse(false);
			return response;
		}
		String remUser = removeFollower.getArgs0().getUsername();
		if (!users.get(username).followed.containsKey(remUser)) {
			response.get_return().setResponse(false);
			return response;

		}
		users.get(username).followed.remove(remUser);
		response.get_return().setResponse(true);

		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyTreasuresFound
	 * @return getMyTreasuresFoundResponse
	 */

	public es.upm.fi.sos.GetMyTreasuresFoundResponse getMyTreasuresFound(
			es.upm.fi.sos.GetMyTreasuresFound getMyTreasuresFound) {

		GetMyTreasuresFoundResponse response = new GetMyTreasuresFoundResponse();

		if (!logged) {
			response.get_return().setResult(false);
		}
		response.get_return().setResult(true);

		User user = users.get(username);

		int size = user.treasuresFound.size();

		double[] alts = new double[size];
		double[] lats = new double[size];
		String[] names = new String[size];
		int i = 0;
		for (Tesoro treasure : user.treasuresFound) {

			alts[i] = treasure.altitude;
			lats[i] = treasure.latitude;
			names[i] = treasure.nombre;

		}
		response.get_return().setAlts(alts);
		response.get_return().setLats(lats);
		response.get_return().setNames(names);
		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyFollowers
	 * @return getMyFollowersResponse
	 */

	public es.upm.fi.sos.GetMyFollowersResponse getMyFollowers(
			es.upm.fi.sos.GetMyFollowers getMyFollowers) {
		
		GetMyFollowersResponse gmfresponse = new GetMyFollowersResponse();
		FollowerList list = new FollowerList();
		String[] arrayFollows;
		
		// Usuario no logeado falla
		if (!logged) {
			gmfresponse.set_return(list);
			return gmfresponse;
		}
		
		arrayFollows = new String [users.get(username).followed.size()];
		int n = 0;
		for (String nombreFollow : users.get(username).followed.keySet()) {
			arrayFollows[n] = nombreFollow;
			n++;
		}
		
		list.setFollowers(arrayFollows);
		gmfresponse.set_return(list);
		return gmfresponse;
		
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyTreasuresCreated
	 * @return getMyTreasuresCreatedResponse
	 */

	public es.upm.fi.sos.GetMyTreasuresCreatedResponse getMyTreasuresCreated(
			es.upm.fi.sos.GetMyTreasuresCreated getMyTreasuresCreated) {
		GetMyTreasuresCreatedResponse response = new GetMyTreasuresCreatedResponse();

		if (!logged) {
			response.get_return().setResult(false);
		}
		response.get_return().setResult(true);

		User user = users.get(username);

		int size = user.treasuresCreated.size();

		double[] alts = new double[size];
		double[] lats = new double[size];
		String[] names = new String[size];
		int i = 0;
		for (Tesoro treasure : user.treasuresCreated) {

			alts[i] = treasure.altitude;
			lats[i] = treasure.latitude;
			names[i] = treasure.nombre;

		}
		response.get_return().setAlts(alts);
		response.get_return().setLats(lats);
		response.get_return().setNames(names);
		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeUser
	 * @return removeUserResponse
	 */

	public es.upm.fi.sos.RemoveUserResponse removeUser(
			es.upm.fi.sos.RemoveUser removeUser) {
		
		String usernameDelete = removeUser.getArgs0().getUsername();
		Response response = new Response();
		RemoveUserResponse removeRes = new RemoveUserResponse();
		
		// Comprobar si el usuario existe, está activo, y es el mismo que se quiere borrar
		if (!users.containsKey(usernameDelete) || !logged || !username.equals(usernameDelete)) {
			response.setResponse(false);
			removeRes.set_return(response);
			return removeRes;
		}
		
		// Si se puede borrar:
		RemoveUser ruser = new RemoveUser();
		RemoveUserE rusere = new RemoveUserE();
		ruser.setName(usernameDelete);
		ruser.setPassword(users.get(usernameDelete).getPassword());
		rusere.setRemoveUser(ruser);
		
		try {
			RemoveUserResponseE rure = cs.removeUser(rusere);
			cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserResponse rur = rure.get_return();
			response.setResponse(rur.getResult());
			removeRes.set_return(response);
			if (!rur.getResult())
				return removeRes;
			// Borrar todas las referencias al usuario en el sistema
			// 1. Borrar tesoros creados por el usuario
			for (int i = 0; i < users.get(usernameDelete).getTreasuresCreated().size(); i++) {
				treasures.remove(users.get(usernameDelete).getTreasuresCreated().get(i).getNombre());
			}
			// 2. Borrar usuario de listas de usuarios que han encontrado un tesoro
			for (int i = 0; i < users.get(usernameDelete).getTreasuresFound().size(); i++) {
				Tesoro treasureAux = treasures.get(users.get(usernameDelete).getTreasuresCreated().get(i));
				HashSet<String> foundAux = treasureAux.getFoundBy();
				foundAux.remove(usernameDelete);
				treasureAux.setFoundBy(foundAux);
				treasures.replace(users.get(usernameDelete).getTreasuresCreated().get(i).getNombre(), treasureAux);
			}
			// 3. Borrar usuario de follows de otros usuarios
			for (String clave:users.keySet()) {
				HashMap<String, User> followedAux = users.get(clave).getFollowed();
				User userAux = users.get(clave);
				if (followedAux.containsKey(usernameDelete)) {
					followedAux.remove(usernameDelete);
					userAux.setFollowed(followedAux);
					users.replace(clave, userAux);
				}
			}
			// 4. Borrar usuario de lista de usuarios
			users.remove(usernameDelete);
			return removeRes;
			
		} catch (RemoteException e) {
			e.printStackTrace();
			response.setResponse(false);
			removeRes.set_return(response);
			return removeRes;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param addFollower
	 * @return addFollowerResponse
	 */

	public es.upm.fi.sos.AddFollowerResponse addFollower(
			es.upm.fi.sos.AddFollower addFollower) {
		
		AddFollowerResponse afresponse = new AddFollowerResponse();
		Response response = new Response();
		String followName = addFollower.getArgs0().getUsername();
		
		// Casos en los que falla la llamada al metodo: el follower no existe, el usuario actual no esta logeado o el follower ya es follower
		if(!users.containsKey(followName) || !logged || users.get(username).followed.containsKey(followName)) {
			response.setResponse(false);
			afresponse.set_return(response);
			return afresponse;
		}
		
		// Si no se da ninguna de las condiciones anteriores, se anyade el follower
		users.get(username).followed.put(followName, users.get(followName));
		response.setResponse(true);
		afresponse.set_return(response);
		return afresponse;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param createTreasure
	 * @return createTreasureResponse
	 */

	public es.upm.fi.sos.CreateTreasureResponse createTreasure(
			es.upm.fi.sos.CreateTreasure createTreasure) {

		CreateTreasureResponse response = new CreateTreasureResponse();
		if (!logged) {

			response.get_return().setResponse(false);
			return response;
		}
		response.get_return().setResponse(true);
		String name = createTreasure.getArgs0().getName();
		double altitude = createTreasure.getArgs0().getAltitude();
		double latitude = createTreasure.getArgs0().getLatitude();

		if (treasures.containsKey(name)) {

			Tesoro t = treasures.get(name);

			t.altitude = altitude;
			t.latitude = latitude;
			return response;
		}

		Tesoro t = new Tesoro(name, altitude, latitude);

		treasures.put(name, t);
		users.get(username).treasuresCreated.add(t);

		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param findTreasure
	 * @return findTreasureResponse
	 */

	public es.upm.fi.sos.FindTreasureResponse findTreasure(
			es.upm.fi.sos.FindTreasure findTreasure) {
		FindTreasureResponse response = new FindTreasureResponse();
		String name = findTreasure.getArgs0().getName();
		if (!logged || !treasures.containsKey(name)) {

			response.get_return().setResponse(false);

			return response;
		}

		Tesoro t = treasures.get(name);
		if (!t.foundBy.contains(username)) {
			users.get(username).treasuresFound.add(t);
			t.foundBy.add(username);
		}
		response.get_return().setResponse(true);

		return response;

	}

	/**
	 * Auto generated method signature
	 * 
	 * @param addUser
	 * @return addUserResponse
	 */

	public es.upm.fi.sos.AddUserResponse addUser(es.upm.fi.sos.AddUser addUser) {

		AddUserResponse response = new AddUserResponse();
		if (logged = false || username != admin) {
			response.get_return().setResponse(false);
			return response;
		}

		AddUser addUserStub = new AddUser();

		UserBackEnd tmp = new UserBackEnd();
		tmp.setName(addUser.getArgs0().getUsername());
		addUserStub.setUser(tmp);

		try {
			cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse csResponse = cs
					.addUser(addUserStub);
			if (csResponse.get_return().getResult()) {

				String name = addUser.getArgs0().getUsername();
				String password = csResponse.get_return().getPassword();

				response.get_return().setPwd(password);
				response.get_return().setResponse(true);

				User user = new User(name, password);
				users.put(name, user);
			} else
				response.get_return().setResponse(false);
			return response;

		} catch (RemoteException e) {
			System.out.println("Error al intentar añadir un usuario.\n");
			e.printStackTrace();
			response.get_return().setResponse(false);
			return response;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getMyFollowerTreasuresCreated
	 * @return getMyFollowerTreasuresCreatedResponse
	 */

	public es.upm.fi.sos.GetMyFollowerTreasuresCreatedResponse getMyFollowerTreasuresCreated(
			es.upm.fi.sos.GetMyFollowerTreasuresCreated getMyFollowerTreasuresCreated) {
		
		String nameFollowed = getMyFollowerTreasuresCreated.getArgs0().getUsername();
		GetMyFollowerTreasuresCreatedResponse gmftcresponse = new GetMyFollowerTreasuresCreatedResponse();
		TreasureList tlist = new TreasureList();
		String[] names;
		double[] lats;
		double[] alts;
		
		// Comprobar usuario loggeado y nameFollowed existe en lista de follows de usuario
		if (!logged || !users.get(username).followed.containsKey(nameFollowed)) {
			tlist.setResult(false);
			gmftcresponse.set_return(tlist);
			return gmftcresponse;
		}
		
		names = new String [users.get(nameFollowed).treasuresCreated.size()];
		lats = new double [users.get(nameFollowed).treasuresCreated.size()];
		alts = new double [users.get(nameFollowed).treasuresCreated.size()];
		for (int i = 0; i < users.get(nameFollowed).treasuresCreated.size() - 1; i++) {
			names[i] = users.get(nameFollowed).treasuresCreated.get(i).getNombre();
			lats[i] = users.get(nameFollowed).treasuresCreated.get(i).getLatitude();
			alts[i] = users.get(nameFollowed).treasuresCreated.get(i).getAltitude();
		}
		
		tlist.setResult(true);
		tlist.setNames(names);
		tlist.setLats(lats);
		tlist.setAlts(alts);
		gmftcresponse.set_return(tlist);
		return gmftcresponse;
		
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param changePassword
	 * @return changePasswordResponse
	 */

	public es.upm.fi.sos.ChangePasswordResponse changePassword(
			es.upm.fi.sos.ChangePassword changePassword) {
		
		PasswordPair passwords = changePassword.getArgs0();
		String vieja = passwords.getOldpwd();
		String nueva = passwords.getNewpwd();
		ChangePasswordResponse cpresponse = new ChangePasswordResponse();
		Response response = new Response();
		
		// Comprobar usuario actual
		if (!logged) {
			response.setResponse(false);
			cpresponse.set_return(response);
			return cpresponse;
		}
		
		// Comprobar que contrasenya antigua = actual
		if (vieja.equals(password)) {
			vieja = users.get(username).getPassword();
		}
		
		// Si se quiere cambiar la de admin, primero comprobar que es el y que antigua es correcta
		if (username.equals(admin) && username != null) {
			if (vieja.equals(adminPWD)) {
				adminPWD = nueva;
				//User user = new User(username, nueva);
				User user = users.get(username);
				user.setPassword(nueva);
				users.replace(username, user);
				response.setResponse(true);
				cpresponse.set_return(response);
				return cpresponse;
			}
			
			else {
				response.setResponse(false);
				cpresponse.set_return(response);
				return cpresponse;
			}
		}
		
		ChangePasswordBackEnd cpbackend = new ChangePasswordBackEnd();
		ChangePasswordResponseE cpresponseE;
		ChangePassword cpassword = new ChangePassword();
		cpbackend.setName(username);
		cpbackend.setOldpwd(vieja);
		cpbackend.setNewpwd(nueva);
		cpassword.setChangePassword(cpbackend);
		
		try {
			
			cpresponseE = cs.changePassword(cpassword);
			cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponse cpr = cpresponseE.get_return();
			
			if (cpr.getResult()) {
				//User usr = new User(username, nueva);
				User user = users.get(username);
				users.replace(username, user);
			}
			
			response.setResponse(cpr.getResult());
			cpresponse.set_return(response);
			return cpresponse;
			
		} catch (RemoteException e) {
			response.setResponse(false);
			cpresponse.set_return(response);
			
			e.printStackTrace();
			System.out.println("Error al cambiar contrasenya.\n");
			return cpresponse;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param login
	 * @return loginResponse
	 */

	public es.upm.fi.sos.LoginResponse login(es.upm.fi.sos.Login login) {

		LoginResponse response = new LoginResponse();

		if (logged) {
			if (username.equals(login.getArgs0().getName())) {
				nsesiones++;
				response.get_return().setResponse(true);
			}
			else
				response.get_return().setResponse(false);
			return response;
		}

		if (login.getArgs0().getName().equals(admin)) {
			if (login.getArgs0().getPwd().equals(adminPWD)) {
				nsesiones++;
				response.get_return().setResponse(true);
			}
			else
				response.get_return().setResponse(false);

			return response;
		}

		LoginBackEnd logBE = new LoginBackEnd();

		logBE.setName(login.getArgs0().getName());
		logBE.setPassword(login.getArgs0().getPwd());
		Login log = new Login();

		log.setLogin(logBE);

		try {
			cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginResponse csResponse = cs
					.login(log);
			csResponse.get_return().getResult();
			response.get_return().setResponse(true);
			nsesiones++;
			return response;
		} catch (RemoteException e) {
			System.out.println("Error al hacer login.\n");
			e.printStackTrace();

			return null;
		}
	}

	public static class User {

		String username;
		String password;

		HashMap<String, User> followed;

		ArrayList<Tesoro> treasuresCreated;
		ArrayList<Tesoro> treasuresFound;

		public User(String username, String password) {

			this.username = username;
			this.password = password;
			this.treasuresCreated = new ArrayList<Tesoro>();
			this.treasuresFound = new ArrayList<Tesoro>();
			this.followed = new HashMap<String, User>();
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public ArrayList<Tesoro> getTreasuresCreated () {
			return treasuresCreated;
		}
		
		public ArrayList<Tesoro> getTreasuresFound () {
			return treasuresFound;
		}
		public HashMap<String,User> getFollowed() {
			return followed;
		}
		
		public void setFollowed(HashMap<String,User> followed) {
			this.followed = followed;
		}
	}

	public static class Tesoro {

		String nombre;
		double latitude;
		double altitude;

		HashSet<String> foundBy;

		public Tesoro(String nombre, double latitude, double altitude) {

			this.nombre = nombre;
			this.latitude = latitude;
			this.altitude = altitude;
			this.foundBy = new HashSet<String>();
		}
		
		public String getNombre() {
			return nombre;
		}
		public double getLatitude() {
			return latitude;
		}
		public double getAltitude() {
			return altitude;
		}
		public HashSet<String> getFoundBy() {
			return foundBy;
		}
		public void setFoundBy(HashSet<String> foundBy) {
			this.foundBy = foundBy;
		}
	}
}


