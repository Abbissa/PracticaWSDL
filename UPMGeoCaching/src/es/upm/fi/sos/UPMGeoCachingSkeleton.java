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

		System.out.println("Crear clase");
		logged = false;
		username = "";
		password = "";
		/*
		 * if (admin != null) admin = "admin"; if (adminPWD != null) adminPWD =
		 * "admin"; if (users != null) users = new HashMap<String, User>();
		 */
		if (users == null)
			users = new HashMap<String, User>();
		if (treasures == null)
			treasures = new HashMap<String, Tesoro>();
		if (!users.containsKey(admin))
			users.put(admin, new User(admin, adminPWD));
		try {
			cs = new UPMAuthenticationAuthorizationWSSkeletonStub();

			// cs._getServiceClient().getOptions().setManageSession(true);
			// cs._getServiceClient().engageModule("addressing");
			System.out.println("Clase creada");
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
		if (username != null && users.containsKey(username)) {
			// Decrementar numero de sesiones de username
			nsesiones--;
			// Si el numero de sesiones es 0, poner username a null
			if (nsesiones == 0) {
				username = null;
				logged = false;
			}
		}
		System.out.println("LOGOUT: nsesiones = " + nsesiones + "; logged = "
				+ logged);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeFollower
	 * @return removeFollowerResponse
	 */
	public es.upm.fi.sos.RemoveFollowerResponse removeFollower(
			es.upm.fi.sos.RemoveFollower removeFollower) {

		System.out.println("REMOVEFOLLOWER 1");
		RemoveFollowerResponse response = new RemoveFollowerResponse();
		Response param = new Response();
		response.set_return(param);
		if (!logged || !checkUser(username)) {
			response.get_return().setResponse(false);
			return response;
		}
		System.out.println("REMOVEFOLLOWER 2");
		String remUser = removeFollower.getArgs0().getUsername();
		if (!users.get(username).followed.containsKey(remUser)) {
			response.get_return().setResponse(false);
			return response;

		}
		System.out.println("REMOVEFOLLOWER 3 borrar followed: " + remUser);
		System.out.println("1");
		System.out.println(users);
		System.out.println(users.get(username));
		System.out.println(users.get(username).followed);
		System.out.println(users.get(username).followed.remove(remUser));
		users.get(username).followed.remove(remUser);
		response.get_return().setResponse(true);
		System.out.println("REMOVEFOLLOWER 4");
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

		System.out.println("\n GET MY TREASURES FOUND");
		GetMyTreasuresFoundResponse response = new GetMyTreasuresFoundResponse();
		TreasureList param = new TreasureList();
		response.set_return(param);

		if (!logged || !checkUser(username)) {
			response.get_return().setResult(false);
			return response;
		}
		response.get_return().setResult(true);

		User user = users.get(username);

		int size = user.treasuresFound.size();

		double[] alts = new double[size];
		double[] lats = new double[size];
		String[] names = new String[size];
		int i = 0;
		System.out.println("\t Tam = " + user.treasuresFound.size());
		// for (Tesoro treasure : user.treasuresFound) {
		for (String treasure : user.treasuresFound) {
			// alts[i] = treasure.altitude;
			alts[i] = treasures.get(treasure).getAltitude();
			System.out.println(i + " " + alts[i]);
			// lats[i] = treasure.latitude;
			lats[i] = treasures.get(treasure).getLatitude();
			System.out.println(i + " " + lats[i]);
			// names[i] = treasure.nombre;
			names[i] = treasures.get(treasure).getNombre();
			System.out.println(i + " " + names[i]);
			i++;

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

		System.out.println("GETMYFOLLOWERS");
		GetMyFollowersResponse gmfresponse = new GetMyFollowersResponse();
		FollowerList list = new FollowerList();
		String[] arrayFollows;

		// Usuario no logeado falla
		if (!logged || !checkUser(username)) {
			list.setResult(false);
			gmfresponse.set_return(list);
			System.out.println("\t Usuario no loggeado");
			return gmfresponse;
		}

		arrayFollows = new String[users.get(username).followed.size()];
		int n = 0;
		for (String nombreFollow : users.get(username).followed.keySet()) {
			arrayFollows[n] = nombreFollow;
			n++;
		}

		list.setResult(true);
		list.setFollowers(arrayFollows);
		gmfresponse.set_return(list);
		System.out.println("\t resultado = "
				+ gmfresponse.get_return().getResult());
		System.out.println("\t primer follower = "
				+ gmfresponse.get_return().getFollowers());
		System.out.println("\t tamanyo lista followed = "
				+ users.get(username).followed.size());
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

		System.out.println("GET MY TREASURES CREATED");
		GetMyTreasuresCreatedResponse response = new GetMyTreasuresCreatedResponse();
		TreasureList param = new TreasureList();
		response.set_return(param);

		if (!logged || !checkUser(username)) {
			response.get_return().setResult(false);
			return response;
		}
		response.get_return().setResult(true);

		User user = users.get(username);

		int size = user.treasuresCreated.size();
		System.out.println("\t Numero de tesoros creados por " + username
				+ " = " + size);

		double[] alts = new double[size];
		double[] lats = new double[size];
		String[] names = new String[size];
		int i = 0;
		for (Tesoro treasure : user.treasuresCreated) {

			alts[i] = treasure.altitude;
			lats[i] = treasure.latitude;
			names[i] = treasure.nombre;
			i++;

		}
		System.out.println(alts.length + " ; " + lats.length + " ; "
				+ names.length);
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

		// Comprobar si el usuario existe, está activo, y es el mismo que se
		// quiere borrar
		System.err.println(!logged);
		if (!users.containsKey(usernameDelete)
				|| !logged || !checkUser(username)
				|| (!username.equals(usernameDelete) && !username
						.equals("admin"))) {
			System.out.println("Fallo");
			response.setResponse(false);
			removeRes.set_return(response);
			return removeRes;
		}

		// Si se puede borrar:
		System.out.println("BORRAR");
		RemoveUser ruser = new RemoveUser();
		RemoveUserE rusere = new RemoveUserE();
		ruser.setName(usernameDelete);
		ruser.setPassword(users.get(usernameDelete).getPassword());
		rusere.setRemoveUser(ruser);

		try {
			RemoveUserResponseE rure = cs.removeUser(rusere);
			cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserResponse rur = rure
					.get_return();
			response.setResponse(rur.getResult());
			removeRes.set_return(response);
			if (!rur.getResult())
				return removeRes;
			// Borrar todas las referencias al usuario en el sistema
			// 1. Borrar tesoros creados por el usuario
			for (int i = 0; i < users.get(usernameDelete).getTreasuresCreated()
					.size(); i++) {
				treasures.remove(users.get(usernameDelete)
						.getTreasuresCreated().get(i).getNombre());
			}
			// 2. Borrar usuario de listas de usuarios que han encontrado un
			// tesoro
			for (int i = 0; i < users.get(usernameDelete).getTreasuresFound()
					.size(); i++) {
				Tesoro treasureAux = treasures.get(users.get(usernameDelete)
						.getTreasuresCreated().get(i));
				HashSet<String> foundAux = treasureAux.getFoundBy();
				foundAux.remove(usernameDelete);
				treasureAux.setFoundBy(foundAux);
				treasures.replace(users.get(usernameDelete)
						.getTreasuresCreated().get(i).getNombre(), treasureAux);
			}
			// 3. Borrar usuario de follows de otros usuarios
			for (String clave : users.keySet()) {
				HashMap<String, User> followedAux = users.get(clave)
						.getFollowed();
				User userAux = users.get(clave);
				if (followedAux.containsKey(usernameDelete)) {
					followedAux.remove(usernameDelete);
					userAux.setFollowed(followedAux);
					users.replace(clave, userAux);
				}
			}

			// Borrar tesoros creados por el usuario de tesoros encontrados por
			// otros usuarios
			for (int i = 0; i < users.get(usernameDelete).treasuresCreated.size(); i++) {
				String tesoroBorrar = users.get(usernameDelete).treasuresCreated.get(i).nombre;
				for (String clave : users.keySet()) {
					if (users.get(clave).treasuresFound.contains(tesoroBorrar))
						users.get(clave).treasuresFound.remove(tesoroBorrar);
				}
			}
			
			// 4. Borrar usuario de lista de usuarios
			users.remove(usernameDelete);
			int i = 0;
			for (String nombre : users.keySet()) {
				System.out.println("Nombre número " + i + " = " + nombre);
			}
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

		// Casos en los que falla la llamada al metodo: el follower no existe,
		// el usuario actual no esta logeado o el follower ya es follower
		if (!users.containsKey(followName) || !logged || !checkUser(username)
				|| users.get(username).followed.containsKey(followName)) {
			response.setResponse(false);
			afresponse.set_return(response);
			return afresponse;
		}

		// Si no se da ninguna de las condiciones anteriores, se anyade el
		// follower
		users.get(username).followed.put(followName, users.get(followName));
		response.setResponse(true);
		afresponse.set_return(response);
		System.out.println("ADDFOLLOWER");
		System.out.println("\t "
				+ users.get(username).getFollowed().get(followName)
				.getPassword());
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
		Response param = new Response();
		response.set_return(param);
		
		
		if (!logged || !checkUser(username)) {

			response.get_return().setResponse(false);
			return response;
		}
		response.get_return().setResponse(true);
		String name = createTreasure.getArgs0().getName();
		double altitude = createTreasure.getArgs0().getAltitude();
		double latitude = createTreasure.getArgs0().getLatitude();

		if (treasures.containsKey(name)) {
			if (users.get(username).getTreasuresCreated().contains(name)) {
				Tesoro t = treasures.get(name);
				t.altitude = altitude;
				t.latitude = latitude;
				return response;
				
			} else {
				response.get_return().setResponse(false);
				return response;
			}
		}

		Tesoro t = new Tesoro(name, altitude, latitude);

		treasures.put(name, t);
		users.get(username).treasuresCreated.add(t);
		int ttt = users.get(username).treasuresCreated.size();
		System.out.println("LISTA CREADOS DE USUARIO " + users.get(username).treasuresCreated.get(ttt - 1).nombre + " ; " + users.get(username).treasuresCreated.get(0).getNombre());

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
		System.out.println("FINDTREASURE");
		FindTreasureResponse response = new FindTreasureResponse();
		Response param = new Response();
		response.set_return(param);
		String name = findTreasure.getArgs0().getName();
		System.out.println(name);


		if (!logged ||!checkUser(username) || !treasures.containsKey(name)) {
			response.get_return().setResponse(false);
			return response;
		}

		Tesoro t = treasures.get(name);
		//System.out.println("\t " + !t.foundBy.contains(username));
		//System.out.println("\t" + username);
		//System.out.println("\t" + t.foundBy.size());
		//for (String s : t.foundBy) {
		//	System.out.println("\t\t " + s);
		//}

		// if (!t.foundBy.contains(username)) {
		/*if (!users.get(username).treasuresFound.contains(t.getNombre())) {
			// users.get(username).treasuresFound.add(t);
			users.get(username).treasuresFound.add(t.nombre);
			System.out.println();
			//t.foundBy.add(username);
			//System.out.println("\t Tamanyo de foundby = " + t.foundBy.size());
			//System.out.println("\t SIZE: "
			//		+ users.get(username).treasuresFound.size());
		}*/
		if (!users.get(username).treasuresFound.contains(name)) {
			users.get(username).treasuresFound.add(name);
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
		es.upm.fi.sos.model.xsd.AddUserResponse param = new es.upm.fi.sos.model.xsd.AddUserResponse();
		response.set_return(param);
		System.out.println("ADDUSER: logged = " + logged + " ; username = "
				+ username + " ; pwd = " + password);
		if (!logged || !checkUser(username) || username == null || !username.equals(admin)) {
			System.out.println("AddUser1");
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
			System.out.println(csResponse.get_return().getResult());
			System.out.println(addUserStub.getUser().getName());
			if (csResponse.get_return().getResult()) {

				String name = addUser.getArgs0().getUsername();
				String password = csResponse.get_return().getPassword();

				response.get_return().setPwd(password);
				response.get_return().setResponse(true);

				User user = new User(name, password);
				users.put(name, user);
			} else {
				System.out.println("AddUser2: getUsername = "
						+ addUser.getArgs0().getUsername());
				response.get_return().setResponse(false);
			}
			return response;

		} catch (RemoteException e) {
			System.out.println("Error al intentar añadir un usuario.\n");
			e.printStackTrace();
			response.get_return().setResponse(false);
			System.out.println("AddUser3");
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

		System.out.println("GET MY FOLLOWER CREATED");
		String nameFollowed = getMyFollowerTreasuresCreated.getArgs0()
				.getUsername();
		GetMyFollowerTreasuresCreatedResponse gmftcresponse = new GetMyFollowerTreasuresCreatedResponse();
		TreasureList tlist = new TreasureList();
		
		System.out.println(users.get(username).followed.size());
		String[] names;
		double[] lats;
		double[] alts;

		// Comprobar usuario loggeado y nameFollowed existe en lista de follows
		// de usuario
		if (!logged || !checkUser(username) || !users.get(username).followed.containsKey(nameFollowed)) {
			tlist.setResult(false);
			gmftcresponse.set_return(tlist);
			System.out.println("\t Fallo del metodo, response = false");
			System.out.println("\t Es amigo = "
					+ users.get(username).followed.containsKey(nameFollowed));
			return gmftcresponse;
		}

		names = new String[users.get(nameFollowed).treasuresCreated.size()];
		System.out.println(names.length);
		lats = new double[users.get(nameFollowed).treasuresCreated.size()];
		System.out.println(lats.length);
		alts = new double[users.get(nameFollowed).treasuresCreated.size()];
		System.out.println(alts.length);
		for (int i = 0; i < users.get(nameFollowed).treasuresCreated.size(); i++) {
			names[i] = users.get(nameFollowed).treasuresCreated.get(i)
					.getNombre();
			lats[i] = users.get(nameFollowed).treasuresCreated.get(i)
					.getLatitude();
			alts[i] = users.get(nameFollowed).treasuresCreated.get(i)
					.getAltitude();
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
		if (!logged || !checkUser(username)) {
			response.setResponse(false);
			cpresponse.set_return(response);
			return cpresponse;
		}

		// Comprobar que contrasenya antigua = actual
		if (vieja.equals(password)) {
			vieja = users.get(username).getPassword();
		}

		// Si se quiere cambiar la de admin, primero comprobar que es el y que
		// antigua es correcta
		if (username.equals(admin) && username != null) {
			if (vieja.equals(adminPWD)) {
				adminPWD = nueva;
				// User user = new User(username, nueva);
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
			cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponse cpr = cpresponseE
					.get_return();

			if (cpr.getResult()) {
				// User usr = new User(username, nueva);
				users.get(username).setPassword(nueva);
				// User user = users.get(username);
				// users.replace(username, user);
				password = nueva;
			}

			response.setResponse(cpr.getResult());
			cpresponse.set_return(response);
			System.out.println("CHANGE PASSWORD:");
			System.out.println("\t Usuario = " + username);
			System.out.println("\t AntiguaPwd = " + vieja);
			System.out.println("\t NuevaPwd = " + password);
			System.out.println("\t PwdLista = "
					+ users.get(username).getPassword());
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
		System.out.println("Inicio login");
		LoginResponse response = new LoginResponse();

		Response aux = new Response();
		response.set_return(aux);

		if (logged) {
			if (username.equals(login.getArgs0().getName())) {
				nsesiones++;

				response.get_return().setResponse(true);
			} else
				response.get_return().setResponse(false);

			return response;
		}

		if (login.getArgs0().getName().equals(admin)) {
			if (login.getArgs0().getPwd().equals(adminPWD)) {
				nsesiones++;
				logged = true;
				username = login.getArgs0().getName();
				password = login.getArgs0().getPwd();
				response.get_return().setResponse(true);
			} else
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
			// response.get_return().setResponse(true);
			aux.setResponse(csResponse.get_return().getResult());
			response.set_return(aux);
			if (aux.getResponse()) {
				username = login.getArgs0().getName();
				password = login.getArgs0().getPwd();
				nsesiones++;
				logged = true;
			}
			return response;
		} catch (RemoteException e) {
			System.out.println("Error en login.\n");
			e.printStackTrace();

			return null;
		}
	}


	private boolean checkUser(String username){

		if(!users.containsKey(username)){
			logged=false;
			return false;
		}		
		return true;

	}

	public static class User {

		String username;
		String password;

		HashMap<String, User> followed;

		ArrayList<Tesoro> treasuresCreated;
		// ArrayList<Tesoro> treasuresFound;
		ArrayList<String> treasuresFound;

		public User(String username, String password) {

			this.username = username;
			this.password = password;
			this.treasuresCreated = new ArrayList<Tesoro>();
			// this.treasuresFound = new ArrayList<Tesoro>();
			this.treasuresFound = new ArrayList<String>();
			this.followed = new HashMap<String, User>();
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public ArrayList<Tesoro> getTreasuresCreated() {
			return treasuresCreated;
		}

		/*
		 * public ArrayList<Tesoro> getTreasuresFound () { return
		 * treasuresFound; }
		 */
		public ArrayList<String> getTreasuresFound() {
			return treasuresFound;
		}

		public HashMap<String, User> getFollowed() {
			return followed;
		}

		public void setFollowed(HashMap<String, User> followed) {
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