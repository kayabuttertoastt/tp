package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindRoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindRoleCommandParser implements Parser<FindRoleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRoleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] roleKeywords = trimmedArgs.split("\\s+");

        return new FindRoleCommand(new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywords)));
    }

}
