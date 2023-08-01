package DEV130_3_UDP_Tekiev;

import java.io.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress IP = InetAddress.getByName(args[0]);
                System.out.println("Ведите строку для отправки на сервер: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String word = reader.readLine();
                byte[] send = word.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(send, send.length, IP, Integer.parseInt(args[1]));
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                clientSocket.receive(receivePacket);
                System.out.println("Время приема сообщения сервером: " + new String(receivePacket.getData()).trim());
                clientSocket.close();
                System.out.println("Клиент был закрыт");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("Для отправки сообщения на сервер введите параметры командной строки в формате \"Название хоста\" \"Номер порта\" ");
        }
    }

}
