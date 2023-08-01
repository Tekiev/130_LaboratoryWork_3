package DEV130_3_UDP_Tekiev;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Server {

    public static void main(String[] args) {
        try {
            for (;;) {
                DatagramSocket socket = new DatagramSocket(50001);
                System.out.println("Сервер запущен и ожидает сообщение от клиента!");
                byte[] receiveDataBuffer = new byte[1024];
                byte[] sendDataBuffer;
                DatagramPacket inputPack = new DatagramPacket(receiveDataBuffer, receiveDataBuffer.length);
                socket.receive(inputPack);
                List<Byte> tmp = new ArrayList<>();
                for (int i = 0; i < receiveDataBuffer.length; i++) {
                    if (receiveDataBuffer[i] != 0) {
                        tmp.add(receiveDataBuffer[i]);
                    }
                }
                byte[] array = new byte[tmp.size()];
                IntStream.range(0, tmp.size()).forEach(i -> array[i] = tmp.get(i));
                String word = new String(array);
                LocalTime timeMessage = LocalTime.now();
                sendDataBuffer = timeMessage.toString().getBytes();
                System.out.println("Время приема сообщения: " + timeMessage + "\n" + "Текст сообщения: " + word + "\n" + "Адрес клиента отправившего сообщение: " + inputPack.getAddress());
                DatagramPacket outputPacket = new DatagramPacket(sendDataBuffer, sendDataBuffer.length, inputPack.getAddress(), inputPack.getPort());
                socket.send(outputPacket);
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
