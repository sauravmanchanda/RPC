import java.net.*;
import java.lang.reflect.Method;
import java.io.*;
import java.lang.*;



public class RPCServer implements Runnable {

   Socket csocket;
   
   RPCServer(Socket csocket) {
      this.csocket = csocket;
   }

   public static void main(String args[]) throws Exception {
      
      ServerSocket ssock = new ServerSocket(6066);
      while (true) {
         Socket sock = ssock.accept();
         new Thread(new RPCServer(sock)).start();
      }
   }
   public void run() {
      try {

         InputStream is = csocket.getInputStream();
         ObjectInputStream ois = new ObjectInputStream(is);

         OutputStream os = csocket.getOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(os);

         String funName = (String)ois.readObject();
         // String funClass = funName.substring(0,1).toUpperCase() + funName.substring(1)+"Class";
         try {
            Class<?> funClass = Class.forName(funName.substring(0,1).toUpperCase() + funName.substring(1)+"Class");
            Method funMethod = funClass.getMethod(funName+"Caller", ObjectInputStream.class, ObjectOutputStream.class);
            funMethod.invoke(null,ois,oos);
         }
         catch (Exception e) {
            System.out.println(e);
         }
         ois.close();
         is.close();
         oos.close();
         os.close();   
         csocket.close();
      }
      catch (Exception e) {
        System.out.println(e);
      }
   }
}