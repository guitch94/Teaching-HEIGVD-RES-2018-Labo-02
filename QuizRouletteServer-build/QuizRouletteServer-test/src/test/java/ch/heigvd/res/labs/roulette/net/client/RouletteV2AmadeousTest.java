package ch.heigvd.res.labs.roulette.net.client;

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
 * @author Julien Biefer et Léo Cortès
 */
public class RouletteV2AmadeousTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingClearCommandShouldClearTheServer() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void askingListOfStudentShouldReturnACorrectList() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void askingListOfStudentWhenThereIsNoStudentShouldReturnAnEmptyList() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void loadingAStudentShouldBeSuccessful() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void loadingAStudentShouldIncreaseTheNumberOfStudentStored() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingByeShouldBeSuccessful() {

  }

  @Test
  @TestAuthor(githubId = "amadeous")
  public void sendingByeShouldReturnTheCorrectNumberOfSendCommands() {

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
    assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
  }
  
}
