

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.*;
//import Servers.java;

public class Client_Schedule_job {
    public static String HELO = "HELO";
    public static String AUTH = "AUTH here";
    public static String REDY = "REDY";
    public static String QUIT = "QUIT";

    public ArrayList <Server> severLISt = new ArrayList<Servers>();
    public Servers biggestServer; 
    public int coreCount = -1; 

    public Client_Schedule_job(){
    
    }

    //method to read a msg from the server returns a string 
    public String readMsg(byte[] b, BufferedInputStream bis) {
        try {
            bis.read(b);
            String str = new String(b, StandCharsets.UTF_8);
            return str;
        }catch (Exception e) {
            Syste.out.println(e);
        }


        return "error";
    }

    
    public static void main (String args []){
        try {
            Socket s = new Socket("127.0.0.1", 50000);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedOutputStream bout = new BufferedOutputStream(bout);
            BufferedInputStream bin = new BufferedInputStream(bin);
            System.out.println("connected");

            Client_Schedule_job cjs = new Client_Schedule_job();

            //send HELO mesg
            //dot.writeBytes(HELO)// commeted by mahmood
            bout.write(HELO.getBytes());
            System.out.println("SENT HELO");
            bout.flush();

            //read the reply for HELO
            String serverReply = cjs.readMsg(new byte[32], bin);
            System.out.println("RCVD in reponse to HELO:" + serverReply);

            //send AUTH msg to server
            bout.write(AUTH.getBytes());
            bout.flush();

            //read the reply AUTH
            serverReply = cjs.readMsg(new byte[32], bin);
            System.out.println("RCVD in response to AUTH:" + serverReply);

            //send REDY msg
            bout.write(REDY.getBytes());
            bout.flush();

            //read the reply to REDY
            serverReply = cjs.readMsg(new byte[32], bin);
            System.out.println("RCVD in response to REDY:" + serverReply);

            //new stuff for wk 5 prac 
            //send GETS msg 
            bout.write("GETS ALL".getBytes());
            bout.flush();
            serverReply = cjs.readMsg(new byte[32],bin);
            System.out.println("RCVD in response to GETS ALL: " + serverReply);
            bout.write("OK".getBytes());
            bout.flush();

            String[] mes_space = serverReply.split(" ");
            serverReply = cls.readMsg(new byte[Integer.parseInt(mes_space[1])*Integer.parseINt(mes_space ;//missing code


            String[] arrOfStr = serverReply.split("\n");

            for(String server: arrOfStr){
                String[] individualServer = server.split(" ");
                Servers ServerIndividual = new Servers();
                serverIndividual.serverName= individualServer[0];
                serverIndividual.serverID = Integer.parseInt(indivdualSever[1]);
                serverIndividual.state = individualServer[2];
                serverIndividual.currStartTime = Integer.praseInt(individualServer[3]);
                serverIndividual.cores = Integer.parseInt(individualServer[4]);
                severIndividual.mem = Integer.parseInt(individualServer[5]);
                severIndividual.disk = Integer.parseInt(individualServer[6]);
                cjs.serverList.add(serverIndividual);
            } // make a list of servers with their attributes

            System.out.println("RCVD in reponse to ok: \n" + serverReply);

            bout.write("OK".getBytes());
            bout.flush();
            serverReply = cjs.readMSg(new byte[1], bin);
            System.out.println("RCVD in reponse to ok should be a dot, is it?:" + serverReply);


            //find biggest server
            for(Servers serverToInspect: cjs.serverList){
             if(cjs.coreCount < serverToInspect.cores){
                    cjs.biggestServer = serverToInspect;
                }
            }
            String bigserver = "SCHD 110 " + cjs.biggestServer.serverName + " " + Interger.toString(cjs.) //missing the end bit of code 
            bout.write(bigserver.getBytes());
            bout.flush();
            System.out.println("the biggest Server is: " +bigServer);
            serverReply = cjs.readMsg(new byte[100], bin);
            System.out.println("RCVD in response to SCHD: " + serverReply);

             //send REDY msg 
            bout.wrtie(REDY.getBytes());
            bout.flush();
            //read the reply to REDY 
            serverReply =cjs.readMsg(new byte[32], bin);
            System.out.println("RCVD in response to REDY: " + serverReply);
            //end of new stuff for wk 5 prac 
            //need a loop for REDY
            //also need to store server and job info 

            //tell server to quit 
            bout.write(QUIT.getBytes());
            bout.flush();

            //read reply 
            serverReply = cjs.readMsg(new byte[32], bin);
            System.out.println("RCVD in response to  QUIT: " + serverReply);

            if(serverReply.equals(QUIT)){
                bout.close();
                dout.close();
                s.close();
            }

        }catch(Exception e){
           System.out.println(e);
        }
    }

}