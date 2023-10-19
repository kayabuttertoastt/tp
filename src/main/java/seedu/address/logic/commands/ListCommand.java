package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import java.util.ArrayList;
import java.util.Set;
import seedu.address.model.person.Role;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Tutorial;
import seedu.address.model.person.Course;
import seedu.address.model.Model;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String enter = "\n      ";
    public static int numPeople = 0;
    public static String s = "";
    ArrayList<Object> nameList = new ArrayList<>();
    ArrayList<Set<Role>> roleList = new ArrayList<>();
    ArrayList<Set<Contact>> contactList = new ArrayList<>();
    ArrayList<Set<Course>> courseList = new ArrayList<>();
    ArrayList<Set<Tutorial>> tutorialList = new ArrayList<>();

    String roleToString(Set<Role> roleList) {
        String begin = "Role: ";
        StringBuilder lst = new StringBuilder(begin);
        roleList.forEach(role -> lst.append(role.toString()).append(", "));
        if(lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    String contactToString(Set<Contact> contactList) {
        String begin = "Contact: ";
        StringBuilder lst = new StringBuilder(begin);
        contactList.forEach(contact -> lst.append(contact.toString()).append(", "));
        if(lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    String courseToString(Set<Course> courseList) {
        String begin = "Courses: ";
        StringBuilder lst = new StringBuilder(begin);
        courseList.forEach(course -> lst.append(course.toString()).append(", "));
        if(lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    String tutorialToString(Set<Tutorial> tutorialList) {
        String begin = "Tutorials: ";
        StringBuilder lst = new StringBuilder(begin);
        courseList.forEach(tutorial-> lst.append(tutorial.toString()).append(", "));
        tutorialList.forEach(tutorial-> lst.append(tutorial.toString()).append(", "));
        if(lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }




    @Override
    public CommandResult execute(Model model) {
        String peopleList = "";

        numPeople = model.getFilteredPersonList().size();
        s = numPeople == 1? "": "s";
        model.getFilteredPersonList().forEach((people) -> {
            nameList.add(people.getName());
            roleList.add(people.getRoles());
            contactList.add(people.getContacts());
            courseList.add(people.getCourses());
            tutorialList.add(people.getTutorials());
        });;

        for (int i = 0; i < nameList.size(); i++) {
            peopleList += (i + 1) +".  Name: " + nameList.get(i) + enter
                    + roleToString(roleList.get(i)) + enter  + contactToString(contactList.get(i)) + enter
                    + courseToString(courseList.get(i)) + enter  + tutorialToString(tutorialList.get(i)) + "\n";
        }

        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult("You have " + numPeople + " profile" + s + " in your list\n" + peopleList);
    }
}
