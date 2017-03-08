package edu.xavier.csci260.dentinger.hw4.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InputImpl implements Input {

  BufferedReader reader;

  public InputImpl(BufferedReader bufferedReader) {
    reader = bufferedReader;
  }

  @Override public List<String> getCommandLine() {

    System.out.println("Please provide some text: ");
    String line = "";
    line = readLine();
    List<String> lines = new ArrayList<String>();

    while (!line.equalsIgnoreCase("")) {
      lines.add(line);
      line = readLine();
    }

    return lines;
  }

  private String readLine() {
    String line = "";
    try {
      line = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return line;
  }

  @Override public List<String> getFileContents(String file) {
    BufferedReader fileReader = null;
    List<String> lines = Collections.EMPTY_LIST;
    try {

      fileReader = new BufferedReader(new FileReader(file));
      lines = fileReader.lines().collect(Collectors.toList());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (fileReader != null) {
        try {
          fileReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return lines;

  }
}
