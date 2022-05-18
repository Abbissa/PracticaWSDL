
/**
 * UPMGeoCachingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package servicio.UPMGeoCaching;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.axis2.AxisFault;

import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.Login;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd;
import es.upm.fi.sos.AddUserResponse;
import es.upm.fi.sos.CreateTreasureResponse;
import es.upm.fi.sos.FindTreasureResponse;
import es.upm.fi.sos.LoginResponse;

/**
 * UPMGeoCachingSkeleton java skeleton for the axisService
 */
public class UPMGeoCachingSkeleton {

        private boolean logged;
        private String username;
        static String admin = null;
        static String adminPWD = null;
        UPMAuthenticationAuthorizationWSSkeletonStub cs;

        private static HashMap<String, User> users;
        private static HashMap<String, Tesoro> treasures;

        public UPMGeoCachingSkeleton() {

                logged = false;
                username = null;
                if (admin != null)
                        admin = "admin";
                if (adminPWD != null)
                        adminPWD = "admin";
                if (users != null)
                        users = new HashMap<String, User>();
                try {
                        cs = new UPMAuthenticationAuthorizationWSSkeletonStub();
                } catch (AxisFault e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        /**
         * Auto generated method signature
         * 
         * @param logout
         * @return
         */
        public void logout(
                        es.upm.fi.sos.Logout logout) {
                // TODO : fill this with the necessary business logic

                logged = false;
                username = null;

        }

        /**
         * Auto generated method signature
         * 
         * @param removeFollower
         * @return removeFollowerResponse
         */
        public es.upm.fi.sos.RemoveFollowerResponse removeFollower(
                        es.upm.fi.sos.RemoveFollower removeFollower) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#removeFollower");
        }

        /**
         * Auto generated method signature
         * 
         * @param getMyTreasuresFound
         * @return getMyTreasuresFoundResponse
         */

        public es.upm.fi.sos.GetMyTreasuresFoundResponse getMyTreasuresFound(
                        es.upm.fi.sos.GetMyTreasuresFound getMyTreasuresFound) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#getMyTreasuresFound");
        }

        /**
         * Auto generated method signature
         * 
         * @param getMyFollowers
         * @return getMyFollowersResponse
         */

        public es.upm.fi.sos.GetMyFollowersResponse getMyFollowers(
                        es.upm.fi.sos.GetMyFollowers getMyFollowers) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#getMyFollowers");
        }

        /**
         * Auto generated method signature
         * 
         * @param getMyTreasuresCreated
         * @return getMyTreasuresCreatedResponse
         */

        public es.upm.fi.sos.GetMyTreasuresCreatedResponse getMyTreasuresCreated(
                        es.upm.fi.sos.GetMyTreasuresCreated getMyTreasuresCreated) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#getMyTreasuresCreated");
        }

        /**
         * Auto generated method signature
         * 
         * @param removeUser
         * @return removeUserResponse
         */

        public es.upm.fi.sos.RemoveUserResponse removeUser(
                        es.upm.fi.sos.RemoveUser removeUser) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#removeUser");
        }

        /**
         * Auto generated method signature
         * 
         * @param addFollower
         * @return addFollowerResponse
         */

        public es.upm.fi.sos.AddFollowerResponse addFollower(
                        es.upm.fi.sos.AddFollower addFollower) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#addFollower");
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

        public es.upm.fi.sos.AddUserResponse addUser(
                        es.upm.fi.sos.AddUser addUser) {

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

                                User user = new User(name,
                                                password);
                                users.put(name, user);
                        } else
                                response.get_return().setResponse(false);
                        return response;

                } catch (RemoteException e) {
                        // TODO Auto-generated catch block
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
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#getMyFollowerTreasuresCreated");
        }

        /**
         * Auto generated method signature
         * 
         * @param changePassword
         * @return changePasswordResponse
         */

        public es.upm.fi.sos.ChangePasswordResponse changePassword(
                        es.upm.fi.sos.ChangePassword changePassword) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#changePassword");
        }

        /**
         * Auto generated method signature
         * 
         * @param login
         * @return loginResponse
         */

        public es.upm.fi.sos.LoginResponse login(
                        es.upm.fi.sos.Login login) {

                LoginResponse response = new LoginResponse();

                if (logged) {
                        if (username.equals(login.getArgs0().getName()))
                                response.get_return().setResponse(true);
                        else
                                response.get_return().setResponse(false);
                        return response;
                }

                if (login.getArgs0().getName().equals(admin)) {
                        if (login.getArgs0().getPwd().equals(adminPWD))
                                response.get_return().setResponse(true);
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
                        return response;
                } catch (RemoteException e) {
                        // TODO Auto-generated catch block
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
    }
}


