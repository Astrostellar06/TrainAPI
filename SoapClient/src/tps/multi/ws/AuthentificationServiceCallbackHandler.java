
/**
 * AuthentificationServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package tps.multi.ws;

    /**
     *  AuthentificationServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AuthentificationServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AuthentificationServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AuthentificationServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for logIn method
            * override this method for handling normal response from logIn operation
            */
           public void receiveResultlogIn(
                    tps.multi.ws.AuthentificationServiceStub.LogInResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from logIn operation
           */
            public void receiveErrorlogIn(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for signIn method
            * override this method for handling normal response from signIn operation
            */
           public void receiveResultsignIn(
                    tps.multi.ws.AuthentificationServiceStub.SignInResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from signIn operation
           */
            public void receiveErrorsignIn(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTicket method
            * override this method for handling normal response from addTicket operation
            */
           public void receiveResultaddTicket(
                    tps.multi.ws.AuthentificationServiceStub.AddTicketResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTicket operation
           */
            public void receiveErroraddTicket(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modifyTicket method
            * override this method for handling normal response from modifyTicket operation
            */
           public void receiveResultmodifyTicket(
                    tps.multi.ws.AuthentificationServiceStub.ModifyTicketResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modifyTicket operation
           */
            public void receiveErrormodifyTicket(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for logOut method
            * override this method for handling normal response from logOut operation
            */
           public void receiveResultlogOut(
                    tps.multi.ws.AuthentificationServiceStub.LogOutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from logOut operation
           */
            public void receiveErrorlogOut(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkTicket method
            * override this method for handling normal response from checkTicket operation
            */
           public void receiveResultcheckTicket(
                    tps.multi.ws.AuthentificationServiceStub.CheckTicketResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkTicket operation
           */
            public void receiveErrorcheckTicket(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getListTicket method
            * override this method for handling normal response from getListTicket operation
            */
           public void receiveResultgetListTicket(
                    tps.multi.ws.AuthentificationServiceStub.GetListTicketResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getListTicket operation
           */
            public void receiveErrorgetListTicket(java.lang.Exception e) {
            }
                


    }
    