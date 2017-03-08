package edu.xavier.csci260.dentinger.hw4;

import edu.xavier.csci260.dentinger.hw4.service.InputImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InputImplTest {

  BufferedReader mockBufferedReader;
  InputImpl sut;

  @Before
  public void setup() {

    mockBufferedReader = Mockito.mock(BufferedReader.class);

    sut = new InputImpl(mockBufferedReader);
  }

  @Test
  public void testCommandLineWithNoInput() throws IOException {

    when(mockBufferedReader.readLine()).thenReturn("");
    assertEquals(0, sut.getCommandLine().size());

    verify(mockBufferedReader, times(1)).readLine();
  }

  @Test
  public void testCommandLineWithSingleLineInput() throws IOException {
    // have mockito first respond with foo then on the next part return empty string
    when(mockBufferedReader.readLine()).thenReturn("foo", "");

    assertEquals(1, sut.getCommandLine().size());
    verify(mockBufferedReader, times(2)).readLine();
  }

  @Test
  public void testCommandLineWithMultipleLineInput() throws IOException {
    // have mockito first respond with foo then on the next part return empty string
    when(mockBufferedReader.readLine()).thenReturn("foo", "each", "");

    assertEquals(2, sut.getCommandLine().size());
    verify(mockBufferedReader, times(3)).readLine();
  }

  @Test
  public void testReadFileWithContent() throws IOException {
    BufferedWriter w = new BufferedWriter(new FileWriter("t"));
    w.write("foo");
    w.newLine();
    w.flush();

    List<String> fileContents = sut.getFileContents("t");
    assertEquals(1, fileContents.size());
    assertEquals("foo", fileContents.get(0));
    w.close();

    File f = new File("t");
    f.delete();
  }

  @Test
  public void testReadFileWithNoContent() throws IOException {
    File f = new File("t");
    f.createNewFile();
    List<String> fileContents = sut.getFileContents("t");
    assertEquals(0, fileContents.size());

    f.delete();
  }

  @Test
  public void testReadFileMultipleContent() throws IOException {
    BufferedWriter w = new BufferedWriter(new FileWriter("t"));
    w.write("foo\n");
    w.write("This is a long string\n");
    w.write("Another");
    w.newLine();
    w.flush();

    List<String> fileContents = sut.getFileContents("t");
    assertEquals(3, fileContents.size());
    assertEquals("foo", fileContents.get(0));
    assertEquals("This is a long string", fileContents.get(1));
    assertEquals("Another", fileContents.get(2));
    w.close();

    File f = new File("t");
    f.delete();
  }
}
