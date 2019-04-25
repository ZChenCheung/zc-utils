package com.zc.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CloseConnectionUtil {

    private CloseConnectionUtil(){}

    public static void closeOutputStream(OutputStream outputStream) {
        closeSocketAndCommunication(null, null, outputStream);
    }

    public static void closeInputStream(InputStream inputStream) {
        closeSocketAndCommunication(null, inputStream, null);
    }

    public static void closeSocket(Socket socket) {
        closeSocketAndCommunication(socket, null, null);
    }

    public static void closeSocketAndInputStream(Socket socket, InputStream inputStream) {
        closeSocketAndCommunication(socket, inputStream, null);
    }

    public static void closeSocketAndOutputStream(Socket socket, OutputStream outputStream) {
        closeSocketAndCommunication(socket, null, outputStream);
    }

    public static void closeSocketAndCommunication(Socket socket,
    		InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
