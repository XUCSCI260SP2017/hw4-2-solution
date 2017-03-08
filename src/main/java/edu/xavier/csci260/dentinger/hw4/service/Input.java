package edu.xavier.csci260.dentinger.hw4.service;

import java.util.List;

public interface Input {
  List<String> getCommandLine();
  List<String> getFileContents(String file);
}
