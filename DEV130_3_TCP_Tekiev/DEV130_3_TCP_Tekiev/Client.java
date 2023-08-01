package DEV130_3_TCP_Tekiev;

import java.io.*;
import java.net.Socket;
public class Client {

    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println("Ведите строку для отправки на сервер: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String word = reader.readLine();
                out.write(word + "\n");
                out.flush();
                String serverWord = in.readLine();
                System.out.println("Время приема сообщения сервером: " + serverWord);
                System.out.println("Клиент был закрыт");
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("Для отправки сообщения на сервер введите параметры командной строки в формате \"Название хоста\" \"Номер порта\" ");
        }
    }
}
