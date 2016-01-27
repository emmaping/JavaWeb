import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/********************************************************************
 * File Name:    SocketHandler.java
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
public class SocketHandler implements Runnable
{
  final static String CRLF = "\r\n";
  private Socket clientSocket;

  public SocketHandler(Socket clientSocket)
  {
    this.clientSocket = clientSocket;
  }

  public void handleSocket(Socket clientSocket) throws IOException
  {
    BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));
    PrintWriter out = new PrintWriter(
        new OutputStreamWriter(clientSocket.getOutputStream()), true);

    String requestHeader = "";
    String s;

    while ((s = in.readLine()) != null)
    {
      s += CRLF;
      requestHeader = requestHeader + s;
      if (s.equals(CRLF))
      {
        break;
      }
    }

    System.out.println("client request is :");
    System.out.println(requestHeader);

    String responseBody = "Client request is :" + requestHeader;
    String responseHeader = "HTTP/1.0 200 OK\r\n" +
        "Content-Type: text/plain; charset=UTF-8\r\n" +
        "Content-Length: " + responseBody.getBytes().length + "\r\n" +
        "\r\n";

    System.out.println("Response Header is:");
    System.out.println(responseHeader);

    out.write(responseHeader);
    out.write(responseBody);
    out.flush();

    out.close();
    in.close();
    clientSocket.close();

  }

  @Override
  public void run()
  {
    try
    {
      handleSocket(clientSocket);
    }
    catch (Exception e)
    {
      // TODO: handle exception
      e.printStackTrace();
    }

  }
  // CONSTANTS ------------------------------------------------------

  // CLASS VARIABLES ------------------------------------------------

  // INSTANCE VARIABLES ---------------------------------------------

  // CONSTRUCTORS ---------------------------------------------------

  // PUBLIC METHODS -------------------------------------------------

  // PROTECTED METHODS ----------------------------------------------

  // PRIVATE METHODS ------------------------------------------------

  // ACCESSOR METHODS -----------------------------------------------

}
