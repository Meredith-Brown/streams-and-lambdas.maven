package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.ReflectionUtils;
import com.zipcodewilmington.streams.tools.logging.LoggerHandler;
import com.zipcodewilmington.streams.tools.logging.LoggerWarehouse;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by leon on 5/29/17.
 * The warehouse is responsible for storing, retrieving, and filtering personSequence
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of
 * this class.
 */
public final class PersonWarehouse implements Iterable<Person> {
    private final LoggerHandler loggerHandler = LoggerWarehouse.getLogger(PersonWarehouse.class);
    private final List<Person> people = new ArrayList<>();

    /**
     * @param person the Person object to add to the warehouse
     * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this method
     */
    public void addPerson(Person person) {
        loggerHandler.disbalePrinting();
        loggerHandler.info("Registering a new person object to the person warehouse...");
        loggerHandler.info(ReflectionUtils.getFieldMap(person).toString());
        people.add(person);
    }

    /**
     * @return list of names of Person objects
     */
    public List<String> getNames() {
        // for each person in people list, getName
        List<String> output = new ArrayList<>(people.size());
        IntStream.range(0, people.size()).forEach(index -> { // stream of Integers between 0 and size
            String name = people.get(index).getName();
            output.add(name);
        });
        return output;
    }

    /**
     * @return list of uniquely named Person objects
     */
    public Stream<Person> getUniquelyNamedPeople() {
        List<String> nameList = new ArrayList<>();
        List<Person> output = new ArrayList<>();
        IntStream.range(0, people.size()).forEach(index -> {
            if (!nameList.contains(people.get(index).getName())) {
                output.add(people.get(index));
                nameList.add(people.get(index).getName());
            }
        });
        return output.stream();
    }

    /**
     * @param character starting character of Person objects' name
     * @return a Stream of respective
     */
    public Stream<Person> getUniquelyNamedPeopleStartingWith(Character character) {
        List<String> nameList = getNames();
        List<Person> output = new ArrayList<>();
        IntStream.range(0, people.size()).forEach(index -> {
            if (people.get(index).getName().charAt(0) == character) {
                output.add(people.get(index));
            }
        });
        return output.stream();
    }

    /**
     * @param n first `n` Person objects
     * @return a Stream of respective
     */
    public Stream<Person> getFirstNUniquelyNamedPeople(int n) {
        List<String> nameList = new ArrayList<>();
        List<Person> output = new ArrayList<>();
        IntStream.range(0, people.size()).forEach(index -> {
            if (!nameList.contains(people.get(index).getName()) && output.size() < n) {
                output.add(people.get(index));
                nameList.add(people.get(index).getName());
            }
        });
        return output.stream();
    }

    /**
     * @return a mapping of Person Id to the respective Person name
     */
    public Map<Long, String> getIdToNameMap() {
        Map<Long, String> output = new HashMap<>();
        IntStream.range(0, people.size()).forEach(index -> {
            output.put(people.get(index).getPersonalId(), people.get(index).getName());
        });
        return output;
    }


    /**
     * @return Stream of Stream of Aliases
     */
    public Stream<Stream<String>> getNestedAliases() {
        List<String> subOutput = new ArrayList<>();
        List<Stream<String>> output = new ArrayList<>();
        IntStream.range(0, people.size()).forEach(index -> {
            String[] aliases = people.get(index).getAliases();
            IntStream.range(0, aliases.length).forEach(index2 -> {
                subOutput.add(aliases[index2]);
                output.add(subOutput.stream());
            });
        });
        return output.stream();
    }

    /**
     * @return Stream of all Aliases
     */
    public Stream<String> getAllAliases() {
        List<String> output = new ArrayList<>();
        IntStream.range(0, people.size()).forEach(index -> {
            String[] aliases = people.get(index).getAliases();
            IntStream.range(0, aliases.length).forEach(index2 -> {
                output.add(aliases[index2]);
            });
        });
        return output.stream();
    }

    // DO NOT MODIFY
    public Boolean contains(Person p) {
        return people.contains(p);
    }

    // DO NOT MODIFY
    public void clear() {
        people.clear();
    }

    // DO NOT MODIFY
    public int size() {
        return people.size();
    }

    @Override // DO NOT MODIFY
    public Iterator<Person> iterator() {
        return people.iterator();
    }
}
