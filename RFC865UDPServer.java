import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
// import java.util.Date;
import java.io.IOException;

public class RFC865UDPServer {
    static DatagramSocket socket;
    static String QUOTE = "Goals transform a random walk into a chase. - Mihaly Csikszentmihalyi";
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        try {
            socket = new DatagramSocket(17);
            } catch (Exception e) {}

        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                byte[] buf = new byte[512];
                DatagramPacket request = new DatagramPacket(buf, buf.length);
                System.out.println("Waiting for request...");
                socket.receive(request);

                /* Process the request */
                String requestContent = new String(buf);
                System.out.println("Received request: " + requestContent);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                System.out.println("From client: " + clientAddress.getCanonicalHostName());

                //
                // 3. Send UDP reply to client
                //
                String replyContent = QUOTE;
                byte[] replyBuf = replyContent.getBytes("UTF-8");
                System.out.println("Reply content: " + replyContent);

                DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length, clientAddress, clientPort);
                System.out.println("Sending reply...");
                socket.send(reply);
                System.out.println("Reply sent");
            } catch (IOException e) {}
        }
    }
}

// public class UDPServer {
//     public static void main(String[] args) {
//         try {
//             DatagramSocket serverSocket = new DatagramSocket(17); // Port number 17 for QOTD protocol
//             byte[] sendData = new byte[1024];
//             while (true) {
//                 String quote = generateQuote();
//                 sendData = quote.getBytes();
//                 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 17);
//                 serverSocket.send(sendPacket);
//             }
//         } catch (Exception e) {
//             System.err.println(e);
//         }
//     }

//     private static String generateQuote() {
//         Date now = new Date();
//         String quote = "QOTD: " + now.toString(); // Replace this with your own quote generation logic
//         return quote;
//     }
// }