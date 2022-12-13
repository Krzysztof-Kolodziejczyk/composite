package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;

public class Cd extends Command {
  public Cd(IContext ctx, ICommand next) {
    super("cd", ctx, next, null, "Użycie cd <sciezka>");
  }
}
