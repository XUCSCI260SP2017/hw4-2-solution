package edu.xavier.csci260.dentinger.hw4.service;

import edu.xavier.csci260.dentinger.platin.PLatin;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dd15475 on 3/7/17.
 */
public class PLatinServiceImpl implements PLatinService {

  private PLatin platin;
  private Input reader;

  public PLatinServiceImpl(PLatin pLatin, Input reader) {
    this.platin = pLatin;
    this.reader = reader;
  }

  @Override public boolean process(String[] args) {
    if (!(args.length > 0)) {
      System.out.println("\n\t\t\tPlease provide an argument to the program.\n");
      return false;
    }
    List<String> dataToConvert;
    if (args[0].equals("-stream")) {
      dataToConvert = reader.getCommandLine();
      dataToConvert.stream().map(data -> platin.process(data)).forEach(System.out::println);
      return true;
    }
    if (args[0].equals("-file") && args.length == 2) {
      dataToConvert = reader.getFileContents(args[1]);
      String outputFileContents = dataToConvert.stream().map(data -> platin.process(data))
          .collect(Collectors.joining("\n"));
      try {
        BufferedWriter w = new BufferedWriter(new FileWriter(args[1] + ".plat"));
        w.write(outputFileContents);
        w.flush();
        w.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return true;
    } else if (args[0].equals("-file") && args.length == 1) {
      System.out.println("\n\t\t\tPlease provide a file name for the -file argument.\n");
      return false;
    }

    return false;
  }
}
