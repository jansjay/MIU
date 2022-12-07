package business;
import dataaccess.*;
public final class Context {
    private static Context context = null;
    private String userId;
    private Auth auth;

    private Context(String userId, Auth auth){
        this.userId = userId;
        this.auth = auth;
    }
    public  Auth getAuth(){return  auth;}
    public  String getUserId(){return userId;}

    static Context createContext(String userId, Auth auth){
        if(context == null){
            //synchronized (Context.context){
                if(context == null){
                    context = new Context(userId,auth);
                }
            //}
        }
        return context;
    }

    /**
     * Call to set a context after logged in.
     * @return
     * @throws LoginException
     */
    public static Context getContext() throws LoginException {
        if(context == null) throw  new LoginException("User yet to login");
        return context;
    }

    /**
     * Clear to context after logged out.
     */
    public void clearContext(){
        this.context = null;
    }
    
    public static boolean isAuth(Auth auth) {
    	try{
    		return getContext() != null && getContext().getAuth() == auth;
    	}
    	catch(LoginException ex) {
    		return false;
    	}
    }
    
    public static boolean isLoggedIn() {
    	try{
    		return getContext() != null && getContext().getAuth() != null;
    	}
    	catch(LoginException ex) {
    		return false;
    	}
    }
}