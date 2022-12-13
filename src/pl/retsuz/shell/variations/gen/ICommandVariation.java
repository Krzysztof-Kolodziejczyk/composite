package pl.retsuz.shell.variations.gen;

import pl.retsuz.shell.gen.ICommand;

public interface ICommandVariation {
    public void processVariation(String params, String path) throws Exception;
    public void setParent(ICommand command);
}
