package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Julien Biefer (Amadeous) et Léo Cortès (Schnoudli)
 */
public class RouletteV2AmadeousTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingClearCommandShouldClearTheServer() throws IOException {
      // As IRouletteV2Client extedns IRouletteV1Client, we can simply cast it
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      client.clear();
      assertEquals(0, client.getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void askingListOfStudentShouldReturnACorrectList() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void askingListOfStudentWhenThereIsNoStudentShouldReturnAnEmptyList() throws IOException {
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      assertEquals(0, client.getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void loadingAStudentShouldBeSuccessful() throws IOException {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void loadingAStudentShouldIncreaseTheNumberOfStudentStored() throws IOException {
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      int beforeLoad = client.getNumberOfStudents();
      client.loadStudent("Test");
      int afterLoad = client.getNumberOfStudents();
      assertTrue(beforeLoad < afterLoad);
  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingByeShouldBeSuccessful() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingByeShouldReturnTheCorrectNumberOfSendCommands() throws IOException, EmptyStoreException {
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      client.getNumberOfStudents();
      client.getProtocolVersion();
      client.pickRandomStudent();
      client.disconnect();

      // Uses the additional method getNumberOfCommands as suggested in the RES 2018 - Annonces Telegram Channel
      assertEquals(4, client.getNumberOfCommands());
  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingInfoShouldBeSuccessful() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingInfoShouldReturnTheCorrectNumberOfStoredStudents() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  // This is an adaptation of the same test for v1
  public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
    assertEquals(RouletteV2Protocol.VERSION, ((IRouletteV2Client)roulettePair.getClient()).getProtocolVersion());
  }
  
}
