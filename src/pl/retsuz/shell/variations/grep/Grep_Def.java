package pl.retsuz.shell.variations.grep;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class Grep_Def extends CommandVariation {

  public Grep_Def(ICommandVariation next, ICommand parent) {
    super(next, parent, "[a-zA-Z0-9.l\\/_,;\"?{}~^&%$#@!~ ]+");
  }

  @Override
  public void make(String path, String params) {
    Composite c = (Composite) (this.getParent().getContext().getCurrent());
    try {
      IComposite elem = c.findElementByPath(path);
      List<String> paramsList = Arrays.stream(params.split(" ")).collect(Collectors.toList());
      Pattern regexp = Pattern.compile(String.join(" ", paramsList.subList(1, paramsList.size() -1 )));
      TextFile tf = ((TextFile) elem);
      String line;
      BufferedReader reader = new BufferedReader(new StringReader(tf.getContent()));
      while ((line = reader.readLine()) != null) {
        if(regexp.matcher(line).find()){
          System.out.println(line);
        }
      }
    } catch (PatternSyntaxException e) {
      System.out.println("Podano nieprawidłowe wyrażenie regularne");
    } catch (Exception e) {
      System.out.println("Docelowy element nie jest plikiem lub nie istnieje.");
    }
  }
}
