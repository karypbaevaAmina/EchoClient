
import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoClient {
    private final int port;
    private final String host;

    private EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static EchoClient connectTo(int port) {
        String localhost = "127.0.0.1";
        return new EchoClient(localhost, port);
    }
    // another methods
    public void run() throws IOException {
        System.out.printf("%nWrite something in English!%n");
        System.out.printf("Write 'bye' to get out!%n%n%n");
        Socket socket = new Socket(host, port);
        Scanner scanner = new Scanner(System.in, "UTF-8");

        try (PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                String message1 = scanner.nextLine();
                writer.write(message1);
                writer.write(System.lineSeparator());

                writer.flush();
                if ("bye".equalsIgnoreCase(message1)) {
                        return;
                    }
                }
            } catch (NoSuchElementException ex) {
                System.out.println("Connection dropped!");
            } catch (IOException e) {
                System.out.printf("Can`t connect to %s:%s !%n", host, port);
                e.printStackTrace();
        }
    }
}


