package edu.xavier.csci260.dentinger.hw4;

import edu.xavier.csci260.dentinger.hw4.service.Input;
import edu.xavier.csci260.dentinger.hw4.service.PLatinService;
import edu.xavier.csci260.dentinger.hw4.service.PLatinServiceImpl;
import edu.xavier.csci260.dentinger.platin.PLatin;
import edu.xavier.csci260.dentinger.platin.PLatinImpl;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PLatinServiceImplTest {

  PLatinService sut;
  Input mockInput;

  @Before
  public void setup() {
    PLatin pLatin = new PLatinImpl();
    mockInput = Mockito.mock(Input.class);

    sut = new PLatinServiceImpl(pLatin, mockInput);
  }

  @Test
  public void testStreamingData() {
    List<String> input = Arrays.asList("foo");
    when(mockInput.getCommandLine()).thenReturn(input);
    String[] args = {"-stream"};
    assertTrue(sut.process(args));

    verify(mockInput).getCommandLine();
  }

  @Test
  public void testFileData() {
    List<String> input = Arrays.asList("foo");
    when(mockInput.getFileContents("t")).thenReturn(input);
    String[] args = {"-file", "t"};
    assertTrue(sut.process(args));
    File output = new File("t.plat");
    assertTrue(output.exists());
    output.delete();
    verify(mockInput).getFileContents("t");
  }

}
