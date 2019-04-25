package com.zc.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CloseConnectionUtil {
    private CloseConnectionUtil(){}

    /**
     * 关闭输出信道
     * @param outputStream
     */
    public static void closeOutputStream(OutputStream outputStream) {
        closeSocketAndCommunication(null, null, outputStream);
    }

    /**
     * 关闭输入信道
     * @param inputStream
     */
    public static void closeInputStream(InputStream inputStream) {
        closeSocketAndCommunication(null, inputStream, null);
    }

    /**
     * 关闭Socket
     * @param socket
     */
    public static void closeSocket(Socket socket) {
        closeSocketAndCommunication(socket, null, null);
    }

    /**
     * 关闭Socket，并且只关闭输入信道
     * @param socket
     * @param inputStream
     */
    public static void closeSocketAndInputStream(Socket socket, InputStream inputStream) {
        closeSocketAndCommunication(socket, inputStream, null);
    }

    /**
     * 关闭Socket，并且只关闭输出信道
     * @param socket
     * @param outputStream
     */
    public static void closeSocketAndOutputStream(Socket socket, OutputStream outputStream) {
        closeSocketAndCommunication(socket, null, outputStream);
    }

    /**
     * 关闭Socket，并且关闭双工通信信道
     * @param socket
     * @param inputStream
     * @param outputStream
     */
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
