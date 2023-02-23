import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RFC865UDPClient {
    static DatagramSocket socket;
    static int QOTD_PORT = 17;
    static String SERVER_NAME = "localhost";
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //


        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
            socket.connect(serverAddress, QOTD_PORT);
            System.out.println("UDP Client connected on port " + QOTD_PORT + " to server: " + serverAddress.getCanonicalHostName());
        } catch (Exception e) {}

        try {
            /* Prepare request content */
            String content = "Lasnier Roman Nicolas, TS3, " + InetAddress.getLocalHost().getHostAddress();
            byte[] buf = content.getBytes("UTF-8");
            System.out.println("Content to send: " + content);
            //
            // 2. Send UDP request to serve
            //
            DatagramPacket request = new DatagramPacket(buf, buf.length);
            System.out.println("Sending request...");
            socket.send(request);
            System.out.println("Request sent to server");

            //
            // 3. Receive UDP reply from server
            //
            byte[] replyBuf = new byte[512];
            DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);
            System.out.println("Waiting for reply...");
            socket.receive(reply);

            /* Process the reply */
            String replyContent = new String(replyBuf);
            System.out.println("Received reply: " + replyContent);
        } catch (IOException e) {}
    }
    
}


// public class UDPClient {
//     public static void main(String[] args) {
//         try {
//             DatagramSocket clientSocket = new DatagramSocket();
//             InetAddress IPAddress = InetAddress.getByName("localhost");
//             byte[] sendData = new byte[1024];
//             byte[] receiveData = new byte[1024];
//             String sentence = "Give me a quote";
//             sendData = sentence.getBytes();
//             DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 17);
//             clientSocket.send(sendPacket);
//             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//             clientSocket.receive(receivePacket);
//             String quote = new String(receivePacket.getData());
//             System.out.println("Quote of the day: " + quote.trim());
//             clientSocket.close();
//         } catch (Exception e) {
//             System.err.println(e);
//         }
//     }
// }

