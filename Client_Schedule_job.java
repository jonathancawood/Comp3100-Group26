import java.net.*;

import java.io.*;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;



class Servers{

    String serverName;

    int serverID;

    String state;

    int currStartTime;

    int cores;

    int mem;

    int disk;





}



public class Client_Schedule_Job {

    

   public ArrayList<Servers> serverList= new ArrayList<Servers>();    

   public Servers biggestServer; 

   public int coreCount = -1; 

   

   public static void main(String args[]){

        try{

            Socket s = new Socket("127.0.0.1", 50000);

            DataInputStream din = new DataInputStream(s.getInputStream());

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            BufferedOutputStream bout = new BufferedOutputStream(dout);

            BufferedInputStream bin = new BufferedInputStream(din);

        

            //Send HELO

            String HELO ="HELO";

            bout.write(HELO.getBytes());

            System.out.println("HELO has been sent to server");

            bout.flush();



            //Receive reply from HELO

            byte[] serverReplyHELO = new byte[32];

            bin.read(serverReplyHELO);

            String ServerReplyHELO = new String(serverReplyHELO, StandardCharsets.UTF_8);

            System.out.println("RCVD in response to HELO: "+ ServerReplyHELO);

            

            //Send AUTH

            String AUTH = "AUTH BLANK";

            bout.write(AUTH.getBytes());

            System.out.println("AUTH has been sent to server");

            bout.flush();



            //Recieve reply from AUTH

            byte[] serverReplyAUTH = new byte[32];

            bin.read(serverReplyAUTH);

            String ServerReplyAUTH = new String(serverReplyAUTH, StandardCharsets.UTF_8);

            System.out.println("RCVD in response to AUTH: " + ServerReplyAUTH);



            //Send REDY

            String REDY ="REDY";

            bout.write(REDY.getBytes());

            System.out.println("REDY has been sent to server");

            bout.flush();



            //Recieve reply from REDY

            byte[] serverReplyREDY = new byte[32];

            bin.read(serverReplyREDY);

            String ServerReplyREDY = new String(serverReplyREDY, StandardCharsets.UTF_8);

            System.out.println("RCVD in reponse to REDY: " +ServerReplyREDY);



            //Send GETS ALL

            String GETS_ALL ="GETS All";

            bout.write(GETS_ALL.getBytes());

            System.out.println("GETS ALL has been sent to server");

            bout.flush();



            //Recieves reply from GETS ALL

            byte[] serverReplyGETS = new byte[32];

            bin.read(serverReplyGETS);

            String ServerReplyGETS = new String(serverReplyGETS, StandardCharsets.UTF_8);

            System.out.println("RCVD in reponse to GETS ALL: " +ServerReplyGETS);

            

            //Send OK (NEEDS TO BE HERE)

            String OK ="OK";

            bout.write(OK.getBytes());

            bout.flush(); 

            

            

            //everything works above here 100%

            String[] temp = ServerReplyGETS.split(" ");

            byte[] serverReplyGETS1 = new byte[Integer.parseInt(temp[1])*Integer.parseInt(temp[2])*Integer.parseInt(temp[3])*Integer.parseInt(temp[4])*Integer.parseInt(temp[5])*Integer.parseInt(temp[6])];

            String ServerReplyGETS1 = new String(serverReplyGETS1, StandardCharsets.UTF_8);





            String[] arrofstr = ServerReplyGETS1.split("\n");

            

            Client_Schedule_Job cjs = new Client_Schedule_Job();



            for(String server: arrofstr){

                String[] individualServer = server.split(" ");

                Servers serverIndividual = new Servers();

                serverIndividual.serverName= individualServer[0];

                serverIndividual.serverID = Integer.parseInt(individualServer[1]);

                serverIndividual.state = individualServer[2];

                serverIndividual.currStartTime = Integer.parseInt(individualServer[3]);

                serverIndividual.cores = Integer.parseInt(individualServer[4]);

                serverIndividual.mem = Integer.parseInt(individualServer[5]);

                serverIndividual.disk = Integer.parseInt(individualServer[6]);

                cjs.serverList.add(serverIndividual);

            }





            // everything up to here seems to be working

            bout.write(OK.getBytes());

            bout.flush();



            byte[] serverReplyOK = new byte[1];

            String ServerReplyOK = new String(serverReplyOK,StandardCharsets.UTF_8);

            System.out.println("RCVD in reponse to ok should be a dot, is it?:" + ServerReplyOK);



            for(Servers serverToInspect: cjs.serverList){

                if(cjs.coreCount < serverToInspect.cores){

                       cjs.biggestServer = serverToInspect;

                   }

               }

            

            String bigserver = "SCHD 110 " + cjs.biggestServer.serverName + " " + Integer.toString(cjs.biggestServer.serverID)+ " " + cjs.biggestServer.state + " " +Integer.toString(cjs.biggestServer.currStartTime) + " " +Integer.toString(cjs.biggestServer.cores) + " " +Integer.toString(cjs.biggestServer.mem) + " " +Integer.toString(cjs.biggestServer.disk) ;

            bout.write(bigserver.getBytes());

            bout.flush();

            System.out.println("the biggest Server is: " + bigserver);



            byte[] serverReplySCHD = new byte[100];                                                       

            bin.read(serverReplySCHD);                                                                    

            String ServerReplySCHD = new String(serverReplySCHD,StandardCharsets.UTF_8);                  

            System.out.println("RCVD in response to SCHD: " + ServerReplySCHD);                           



            //send REDY msg 

            bout.write(REDY.getBytes());

            bout.flush();



            byte[] serverReplyREDY1 = new byte[32];                                                       

            bin.read(serverReplyREDY1);                                                                   

            String ServerReplyREDY1 = new String(serverReplyREDY1,StandardCharsets.UTF_8);                

            System.out.println("RCVD in response to SCHD: " + ServerReplyREDY1);                          



            //tell server to quit 

            String QUIT = "QUIT";                                                                        

            bout.write(QUIT.getBytes());

            bout.flush();



            //read reply  

            byte[] serverReplyQUIT = new byte[32];                                                      

            bin.read(serverReplyQUIT);                                                                  

            String ServerReplyQUIT = new String(serverReplyQUIT,StandardCharsets.UTF_8);                

            System.out.println("RCVD in response to QUIT: " + ServerReplyQUIT); 



            if(ServerReplyQUIT.equals(QUIT)){

                bout.close();

                dout.close();

                s.close();

            }



        }catch (Exception e){

           System.out.println(e);

        }

    }

}
