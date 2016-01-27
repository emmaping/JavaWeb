import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************************************
 * File Name:    MyHTTPServer.java
 *
 * Date Created: Jan 27, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class MyHTTPServer
{
  public static void main(String[] args) throws Exception
  {
    int port = 8888;
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Start Service, bind port:" + port);
    ExecutorService fixThreadPool = Executors.newFixedThreadPool(10);

    while (true)
    {
      Socket clientSocket = serverSocket.accept();
      System.out.println("new connection " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

      try
      {
        fixThreadPool.execute(new SocketHandler(clientSocket));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

}
