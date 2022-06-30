import java.net.*;
import java.io.*;


public class Server {
    public static void main(String[] args){
        System.out.println("************************************");
        System.out.println("HELLO.. THIS IS SERVER...");
        System.out.println("************************************");
        System.out.println(" ");
        try {
            int port = 8088;
            int b = 3;
            double clientP, clientG, clientA, B, secKey;

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());

            System.out.println("From Server : Private Key = " + b);

            DataInputStream in = new DataInputStream(server.getInputStream());

            clientP = Integer.parseInt(in.readUTF());
            System.out.println("From Client : P = " + clientP);

            clientG = Integer.parseInt(in.readUTF());
            System.out.println("From Client : G = " + clientG);

            clientA = Double.parseDouble(in.readUTF());
            System.out.println("From Client : Public Key = " + clientA);

            B = ((Math.pow(clientG, b)) % clientP);

            OutputStream outToclient = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToclient);

            out.writeUTF(Double.toString(B));

            secKey = ((Math.pow(clientA, b)) % clientP);

            System.out.println("Secret Key to perform Symmetric Encryption = "+ secKey);
            server.close();
        } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
        } catch (IOException e) {

        }
    }
}

