package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;

public class Diff extends Command {

  public Diff(IContext ctx, ICommand next) {
    super("diff *([a-zA-Z0-9.l\\/_]*)", ctx, next, null, "Użycie diff <sciezka>");
  }

}
