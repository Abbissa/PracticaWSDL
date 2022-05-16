
/**
 * UPMGeoCachingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package servicio.UPMGeoCaching;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.Login;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd;
import cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd;
import es.upm.fi.sos.AddUserResponse;
import es.upm.fi.sos.LoginResponse;

/**
 * UPMGeoCachingSkeleton java skeleton for the axisService
 */
public class UPMGeoCachingSkeleton {

        boolean logged;
        String username;
        String admin;
        String adminPWD;
        UPMAuthenticationAuthorizationWSSkeletonStub cs;

        public UPMGeoCachingSkeleton() {

                logged = false;
                username = null;
                admin = "ADMIN";
                adminPWD = "ADMIN";
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
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#createTreasure");
        }

        /**
         * Auto generated method signature
         * 
         * @param findTreasure
         * @return findTreasureResponse
         */

        public es.upm.fi.sos.FindTreasureResponse findTreasure(
                        es.upm.fi.sos.FindTreasure findTreasure) {
                // TODO : fill this with the necessary business logic
                throw new java.lang.UnsupportedOperationException(
                                "Please implement " + this.getClass().getName() + "#findTreasure");
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

                AddUser user = new AddUser();

                UserBackEnd tmp = new UserBackEnd();
                tmp.setName(addUser.getArgs0().getUsername());
                user.setUser(tmp);

                try {
                        cliente.UpmAuthenticationAuthorization.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse csResponse = cs
                                        .addUser(user);
                        response.get_return().setPwd(csResponse.get_return().getPassword());
                        response.get_return().setResponse(true);

                        return response;

                } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                        return null;
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
}
