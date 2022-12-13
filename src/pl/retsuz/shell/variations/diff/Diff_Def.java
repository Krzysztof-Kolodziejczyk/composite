package pl.retsuz.shell.variations.diff;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Diff_Def extends CommandVariation {

  public Diff_Def(ICommandVariation next, ICommand parent) {
    super(next, parent, "[a-zA-Z0-9.l\\/_]*");
  }

  @Override
  public void make(String path, String params) {
    Composite c = (Composite) (this.getParent().getContext().getCurrent());
    try {
      BufferedReader reader1 = new BufferedReader(new StringReader(((TextFile) c.findElementByPath(path)).getContent()));
      BufferedReader reader2 = new BufferedReader(new StringReader(((TextFile) c.findElementByPath(params.split(" ")[2])).getContent()));
      diff(reader1, reader2);
    } catch (PatternSyntaxException e) {
      System.out.println("Podano nieprawidłowe wyrażenie regularne");
    } catch (Exception e) {
      System.out.println("Docelowy element nie jest plikiem lub nie istnieje.");
    }
  }

  private void diff(BufferedReader file1Reader, BufferedReader file2Reader) throws IOException {
    List<String> file1 = new ArrayList<>();
    List<String> file2 = new ArrayList<>();
    String buffer;
    while ((buffer = file1Reader.readLine()) != null){
      file1.add(buffer);
    }
    while ((buffer = file2Reader.readLine()) != null){
      file2.add(buffer);
    }

    List<String> longest = file1.size() > file2.size() ? file1 : file2;
    List<String> shortest = file1.size() <= file2.size() ? file1 : file2;

    for(int i=0; i<shortest.size(); i++){
      String message;
      if(longest.get(i).compareTo(shortest.get(i)) == 0){
        message = longest.get(i);
      }else {
        if(!shortest.contains(longest.get(i))){
          message = "---";
        }else {
          message = i + "a" + shortest.indexOf(longest.get(i));
        }
      }
      System.out.println(message);
    }
    for(int i=shortest.size(); i<longest.size(); i++){
      System.out.println(longest.get(i));
    }

  }

}
