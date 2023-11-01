package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all favourite persons in the address book to the user.
 */
public class FavListCommand extends Command {
    public static final String MESSAGE_SUCCESS = "You have 0 favourite profile in your list.\n";
    public static final String COMMAND_WORD = "favlist";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Person> favoritedPredicate = person -> person.getFavourite().getFavourite();

        model.updateFilteredPersonList(favoritedPredicate);

        List<Person> favouritePersons = model.getFilteredPersonList();

        int numbFavouritePeople = favouritePersons.size();
        String s = numbFavouritePeople <= 1 ? "" : "s";

        String peopleList = favouritePersons.stream()
                .map(this::formatPersonDetails)
                .collect(Collectors.joining("\n"));

        return new CommandResult("You have " + numbFavouritePeople
                + " favourite profile" + s + " in your list.\n" + peopleList);
    }

    private String formatPersonDetails(Person person) {
        return "Name: " + person.getName() + "\n"
                + "Roles: " + person.getRoles() + "\n"
                + "Contacts: " + person.getContacts() + "\n"
                + "Courses: " + person.getCourses() + "\n"
                + "Tutorials: " + person.getTutorials() + "\n";
    }
}

