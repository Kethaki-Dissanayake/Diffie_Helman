import java.net.*;
import java.io.*;



public class Client {

    public static void main(String[] args)
    {
        System.out.println("************************************");
        System.out.println("HELLO.. THIS IS CLIENT...");
        System.out.println("************************************");
        System.out.println(" ");

        try {
            String serverName = "localhost";
            int port = 8088;

            int p = 53;
            int g = 12;
            int a = 4;
            double secKey, serverB;

            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to "+ client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF(Integer.toString(p));

            out.writeUTF(Integer.toString(g));

            double A = ((Math.pow(g, a)) % p);
            out.writeUTF(Double.toString(A));

            System.out.println("From Client : Private Key = " + a);

            DataInputStream in = new DataInputStream(client.getInputStream());

            serverB = Double.parseDouble(in.readUTF());
            System.out.println("From Server : Public Key = " + serverB);

            secKey = ((Math.pow(serverB, a)) % p);

            System.out.println("Secret Key to perform Symmetric Encryption = "+ secKey);
            client.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}