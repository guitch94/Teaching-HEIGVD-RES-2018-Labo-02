package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.data.StudentsList;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import java.io.IOException;
import java.util.List;

/**
 * This class implements the client side of the protocol specification (version 2).
 *
 * @author Olivier Liechti
 */
public class RouletteV2ClientImpl extends RouletteV1ClientImpl implements IRouletteV2Client {

  private int numberOfStudentAdded = 0;
  private int numberOfCommands = 0;
  private boolean successOfCommand = false;

  @Override
  public void clearDataStore() throws IOException {
      out.println(RouletteV2Protocol.CMD_CLEAR);
      out.flush();
      numberOfCommands++;
      br.readLine();
     }

  @Override
  public List<Student> listStudents() throws IOException {
      numberOfCommands++;
      out.println(RouletteV2Protocol.CMD_LIST);
      out.flush();
      String reponse = br.readLine();
      return JsonObjectMapper.parseJson(reponse, StudentsList.class).getStudents();
     }

  public int getNumberOfStudentAdded(){
    return numberOfStudentAdded;
  }

  public  int getNumberOfCommands(){
    return numberOfCommands;
  }

  public boolean checkSuccessOfCommand(){
      return successOfCommand;
  }

}
