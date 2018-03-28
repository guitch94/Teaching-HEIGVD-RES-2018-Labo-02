package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.InfoCommandResponse;
import ch.heigvd.res.labs.roulette.net.protocol.RandomCommandResponse;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the client side of the protocol specification (version 1).
 *
 * @author Olivier Liechti
 * @author Christophe Joyet
 */
public class RouletteV1ClientImpl implements IRouletteV1Client {

  private static final Logger LOG = Logger.getLogger(RouletteV1ClientImpl.class.getName());
  Socket clientSocket                   = null; //creation of client socket
  private BufferedReader bufferedReader = null;
  private PrintWriter printWriter       = null;



  @Override
  public void connect(String server, int port) throws IOException {
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //Declaration of the variables
      clientSocket   = new Socket(server, port);
      bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      printWriter    = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
      bufferedReader.readLine(); // read first lign with "Hello"
  }

  @Override
  public void disconnect() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    printWriter.println(RouletteV1Protocol.CMD_BYE);
    //close socket, BufferedReader and PrintWriter
    clientSocket.close();
    bufferedReader.close();
    printWriter.close();
  }

  @Override
  public boolean isConnected() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      return clientSocket != null && clientSocket.isConnected();
  }

  @Override
  public void loadStudent(String fullname) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    printWriter.println(RouletteV1Protocol.CMD_LOAD);
    bufferedReader.readLine();
    printWriter.println(fullname);
    printWriter.println(RouletteV1Protocol.CMD_LOAD_ENDOFDATA_MARKER);
    bufferedReader.readLine();

  }

  @Override
  public void loadStudents(List<Student> students) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    printWriter.println(RouletteV1Protocol.CMD_LOAD);
    bufferedReader.readLine();
    for (Student s : students) {
      printWriter.println(s.getFullname());
    }
    printWriter.println(RouletteV1Protocol.CMD_LOAD_ENDOFDATA_MARKER);
    bufferedReader.readLine();

  }

  @Override
  public Student pickRandomStudent() throws EmptyStoreException, IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    printWriter.println(RouletteV1Protocol.CMD_RANDOM);
    RandomCommandResponse response = JsonObjectMapper.parseJson(bufferedReader.readLine(), RandomCommandResponse.class);

    if (!response.getError().isEmpty()) {
      LOG.log(Level.SEVERE, "There is no student");
      throw new EmptyStoreException();
    }

    return Student.fromJson(response.getFullname());
  }

  @Override
  public int getNumberOfStudents() throws IOException {
   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      printWriter.println(RouletteV1Protocol.CMD_INFO);
      InfoCommandResponse response = JsonObjectMapper.parseJson(bufferedReader.readLine(), InfoCommandResponse.class);
      return response.getNumberOfStudents();
  }

  @Override
  public String getProtocolVersion() throws IOException {
   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      printWriter.println(RouletteV1Protocol.CMD_INFO);
      InfoCommandResponse response = JsonObjectMapper.parseJson(bufferedReader.readLine(), InfoCommandResponse.class);
      return response.getProtocolVersion();
  }


}
