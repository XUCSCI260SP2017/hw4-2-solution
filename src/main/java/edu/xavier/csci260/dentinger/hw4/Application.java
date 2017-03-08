package edu.xavier.csci260.dentinger.hw4;

import edu.xavier.csci260.dentinger.hw4.service.Input;
import edu.xavier.csci260.dentinger.hw4.service.InputImpl;
import edu.xavier.csci260.dentinger.hw4.service.PLatinService;
import edu.xavier.csci260.dentinger.hw4.service.PLatinServiceImpl;
import edu.xavier.csci260.dentinger.platin.PLatin;
import edu.xavier.csci260.dentinger.platin.PLatinImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
  public static void main(String[] args) throws IOException {

    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    PLatin pLatin = new PLatinImpl();
    Input input = new InputImpl(br);

    PLatinService pLatinService = new PLatinServiceImpl(pLatin,input);
    pLatinService.process(args);

    br.close();
  }
}
