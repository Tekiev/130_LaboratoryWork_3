package DEV130_3_TCP_Tekiev;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) {
        try {
            for (;;) {
                ServerSocket socket = new ServerSocket(3345);
                System.out.println("Сервер запущен и ожидает сообщение от клиента!");
                Socket clientSocket = socket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String word = in.readLine();
                LocalTime timeMessage = LocalTime.now();
                System.out.println("Время приема сообщения: " + timeMessage + "\n" + "Текст сообщения: " + word + "\n" + "Адрес клиента отправившего сообщение: " + clientSocket.getLocalAddress());
                out.write(timeMessage.toString());
                out.flush();
                clientSocket.close();
                in.close();
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
