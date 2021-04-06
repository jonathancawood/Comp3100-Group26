import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.*;

//unsure if these extra ones are needed 
//import java.net.Socket;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.DataInputStream;
//import java.util.*;
//import java.nio.*;
//import Servers.java;

public class Client_Schedule_job {
    public static String HELO = "HELO";                                                      //moved 
    public static String AUTH = "AUTH here";                                                 //moved
    public static String REDY = "REDY";                                                      //moved
    public static String QUIT = "QUIT";                                                      //moved
    //not needed ^

    
    public ArrayList<Servers> serverList = new ArrayList<Servers>();
    public Servers biggestServer; 
    public int coreCount = -1; 

    public Client_Schedule_job(){
    
    }

    //method to read a msg from the server returns a string                                  //replaced tried to replaced everytime readMsg gets called
    public String readMsg(byte[] b, BufferedInputStream bis) {                               //replaced
        try {                                                                                //replaced
            bis.read(b);                                                                     //repalced
            String str = new String(b, StandardCharsets.UTF_8);                              //replaced
            return str;                                                                      //replaced
        }catch (Exception e) {                                                               //repalced 
            System.out.println(e);                                                           //replaced
        }                                                                                    //replaced
        return "error";                                                                      //replaced
    }                                                                                        //repalced 
    //^not needed

    
    public static void main (String args []){
        try {
            Socket s = new Socket("127.0.0.1", 50000);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedOutputStream bout = new BufferedOutputStream(bout);
            BufferedInputStream bin = new BufferedInputStream(bin);
            System.out.println("connected");

            ClientJobScheduler cjs = new ClientJobScheduler();

            //send HELO mesg
            //dot.writeBytes(HELO)// commeted by mahmood
            String HELO = "HELO";                                                            //added from move
            bout.write(HELO.getBytes());
            System.out.println("SENT HELO");
            bout.flush();

            //read the reply for HELO
            //String serverReply = cjs.readMsg(new byte[32], bin);
            //System.out.println("RCVD in reponse to HELO:" + serverReply);
            byte[] serverReplyHELO = new byte[32];                                          //new trying to replace readMsg
            bin.read(serverReplyHELO);                                                      //new
            String ServerReplyHELO = new String(serverReplyHELO,StandardCharsets.UTF_8);    //new
            System.out.println("RCVD in reponse to HELO:" + serverReplyHELO);               //new

            //send AUTH msg to server
            String AUTH = "AUTH BLANK";                                                     //added from move
            bout.write(AUTH.getBytes());
            bout.flush();

            //read the reply AUTH
            //serverReply = cjs.readMsg(new byte[32], bin);
            //System.out.println("RCVD in response to AUTH:" + serverReply);
            byte[] serverReplyAUTH = new byte[32];                                         //new trying to replace readMsg
            bin.read(serverReplyAUTH);                                                     //new
            String ServerReplyAUTH = new String(serverReplyAUTH,StandardCharsets.UTF_8);   //new
            System.out.println("RCVD in response to AUTH:" + ServerReplyAUTH);             //new

            //send REDY msg
            String REDY = "REDY";                                                          //added from move
            bout.write(REDY.getBytes());
            bout.flush();

            //read the reply to REDY
            //serverReply = cjs.readMsg(new byte[32], bin);
            //System.out.println("RCVD in response to REDY:" + serverReply);
            byte[] serverReplyREDY = new byte[32];                                         //new trying to replace readMsg
            bin.read(serverReplyREDY);                                                     //new
            String ServerReplyREDY = new String(serverReplyREDY,StandardCharsets.UTF_8);   //new
            System.out.println("RCVD in response to REDY:" + ServerReplyREDY);             //new

            //new stuff for wk 5 prac 
            //send GETS msg 
            String GETS_ALL = "GETS ALL";                                                  //added
            bout.write("GETS ALL".getBytes());
            bout.flush();

            //server reply to GETS ALL
            //serverReply = cjs.readMsg(new byte[32],bin);
            //System.out.println("RCVD in response to GETS ALL: " + serverReply);
            byte[] serverReplyGETS = new byte[32];                                         //new trying to replace readMsg
            bin.read(serverReplyGETS);                                                     //new
            String ServerReplyGETS = new String(serverReplyGETS,StandardCharsets.UTF_8);   //new
            System.out.println("RCVD in response to GETS ALL:" + ServerReplyGETS);         //new
            
            //send ok to GETS ALL 
            String OK ="OK";                                                               //added
            bout.write(OK.getBytes());
            bout.flush();

            //String[] mes_space = serverReply.split(" ");
            //serverReply = cjs.readMsg(new byte[Integer.parseInt(mes_space[1])*Integer.parseInt(mes_spac //missing code 
            //serverReply = cjs.readMsg(new byte[Integer.parseInt(mes_space[1])*Integer.parseInt(mes_space[2])*Integer.parseInt(mes_space[3])*Integer.parseInt(mes_space[4])*Integer.parseInt(mes_space[5])*Integer.parseInt(mes_space[6])], bin );  //missing code (i think this code is right)

            String[] mes = ServerReplyGETS.split(" ");      
            // bin.read(ServerReplyGETS); //unsure about the need for this                                                                                                                              //new
            byte[] serverReplyGETS1  = new byte[Integer.parseInt(mes[1])*Integer.parseInt(mes[2])*Integer.parseInt(mes[3])*Integer.parseInt(mes[4])*Integer.parseInt(mes[5])*Integer.parseInt(mes[6])]; //new
            String ServerReplyGETS1 = new String(serverReplyGETS1, StandardCharsets.UTF_8);                                                                                                             //new

            //String[] arrOfStr = serverReply.split("\n");
            String[] arrOfStr = ServerReplyGETS1.split("\n");

            for(String server: arrOfStr){
                String[] individualServer = server.split(" ");
                Servers ServerIndividual = new Servers();
                serverIndividual.serverName= individualServer[0];
                serverIndividual.serverID = Integer.parseInt(individualSever[1]);
                serverIndividual.state = individualServer[2];
                serverIndividual.currStartTime = Integer.praseInt(individualServer[3]);
                serverIndividual.cores = Integer.parseInt(individualServer[4]);
                severIndividual.mem = Integer.parseInt(individualServer[5]);
                severIndividual.disk = Integer.parseInt(individualServer[6]);
                cjs.serverList.add(serverIndividual);
            } // make a list of servers with their attributes

            //System.out.println("RCVD in reponse to ok: \n" + serverReply);
            System.out.println("RCVD in reponse to ok: \n" + ServerReplyGETS1);

            bout.write(OK.getBytes());                                                                  //changed
            bout.flush();
            //serverReply = cjs.readMSg(new byte[1], bin);
            //System.out.println("RCVD in reponse to ok should be a dot, is it?:" + serverReply);
            byte[] serverReplyOK = new byte[1];                                                         //new trying to replace readMsg
            bin.read(serverReplyOK);                                                                    //new
            String ServerReplyOK = new String(serverReplyOK,StandardCharsets.UTF_8);                    //new
            System.out.println("RCVD in reponse to ok should be a dot, is it?:" + ServerReplyOK);       //new

            //find biggest server
            for(Servers serverToInspect: cjs.serverList){
             if(cjs.coreCount < serverToInspect.cores){
                    cjs.biggestServer = serverToInspect;
                }
            }
            //String bigserver = "SCHD 110 " + cjs.biggestServer.serverName + " " + Integer.tostring(cjs ........                         //missing the end bit of code  //serverName,serverID,state,currStartTime,cores,mem,disk
            String bigserver = "SCHD 110 " + cjs.biggestServer.serverName + " " + Integer.tostring(cjs.biggestServer.serverId)+ " " + cjs.biggestServer.state + " " +Integer.tostring(cjs.biggestServer.currStartTime) + " " +Integer.tostring(cjs.biggestServer.cores) + " " +Integer.tostring(cjs.biggestServer.mem) + " " +Integer.tostring(cjs.biggestServer.disk) ;
            bout.write(bigserver.getBytes());
            bout.flush();
            System.out.println("the biggest Server is: " + bigServer);

            //serverReply = cjs.readMsg(new byte[100], bin);
            //System.out.println("RCVD in response to SCHD: " + serverReply);
            byte[] serverReplySCHD = new byte[100];                                                       //new trying to replace readMsg
            bin.read(serverReplySCHD);                                                                    //new
            String ServerReplySCHD = new String(serverReplySCHD,StandardCharsets.UTF_8);                  //new
            System.out.println("RCVD in response to SCHD: " + ServerReplySCHD);                           //new

             //send REDY msg 
            bout.write(REDY.getBytes());
            bout.flush();

            //read the reply to REDY 
            //serverReply =cjs.readMsg(new byte[32], bin);
            //System.out.println("RCVD in response to REDY: " + serverReply);
            byte[] serverReplyREDY1 = new byte[32];                                                       //new trying to replace readMsg
            bin.read(serverReplyREDY1);                                                                   //new
            String ServerReplyREDY1 = new String(serverReplyREDY1,StandardCharsets.UTF_8);                //new
            System.out.println("RCVD in response to SCHD: " + ServerReplyREDY1);                          //new


            //end of new stuff for wk 5 prac 
            //need a loop for REDY                      //might be old 
            //also need to store server and job info    //might be old 

            //tell server to quit 
            String QUIT = "QUIT";                                                                        //added from move
            bout.write(QUIT.getBytes());
            bout.flush();

            //read reply 
            //serverReply = cjs.readMsg(new byte[32], bin);
            //System.out.println("RCVD in response to  QUIT: " + serverReply);  
            byte[] serverReplyQUIT = new byte[32];                                                      //new trying to replace readMsg
            bin.read(serverReplyQUIT);                                                                  //new
            String ServerReplyQUIT = new String(serverReplyQUIT,StandardCharsets.UTF_8);                //new
            System.out.println("RCVD in response to QUIT: " + ServerReplyQUIT);                         //new
            
            //if(serverReply.equals(QUIT)){
            if(ServerReplyQUIT.equals(QUIT)){
                bout.close();
                dout.close();
                s.close();
            }

        }catch(Exception e){
           System.out.println(e);
        }
    }

}
//
