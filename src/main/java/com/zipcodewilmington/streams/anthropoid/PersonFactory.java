package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.RandomUtils;
import com.zipcodewilmington.streams.tools.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by leon on 5/1/17.
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonFactory {
    public PersonFactory() {
        /** this class is not to be instantiated */
    }

    /**
     * @return a new instance of a person with fields of random values
     */
    public Person createRandomPerson() {
        String name = StringUtils.capitalizeFirstChar(RandomUtils.createString('a', 'e', 3));
        String[] aliases = RandomUtils.createStrings('a', 'z', 3, 5);
        boolean isMale = RandomUtils.createBoolean(50);
        long personalId = System.nanoTime();
        Date birthDate = RandomUtils.createDate(1950, 2010);

        Person randomPerson = new Person(name, isMale, personalId, birthDate, aliases);
        return randomPerson;
    }

    /**
     * Section 8.8
     *
     * @param listSize - number of Person objects to create
     * @return - ArrayList of Person objects
     */
    public List<Person> createPersonList(int listSize) {
        List<Person> output = new ArrayList<>(listSize);
        IntStream.range(0, listSize).forEach(index -> {
            Person person = createRandomPerson();
            output.add(person);
        });
        return output;
    }

    /**
     * @param arrayLength - number of Person objects to create
     * @return - Array of Person objects
     */ // https://stackoverflow.com/questions/25077203/java-8-fill-array-with-supplier
    public Person[] createPersonArray(int arrayLength) {
        Supplier<Person> supplier = () -> createRandomPerson();
        Person[] output = Stream.generate(supplier).limit(arrayLength).toArray(Person[]::new);
        return output;
    }

    /**
     * Section 8.2
     *
     * @param streamCount - number of Person objects to create
     * @return - Stream representation of collection of Person objects
     */
    public Stream<Person> createPersonStream(int streamCount) {
        List<Person> personStreamSource = createPersonList(streamCount);
        return personStreamSource.stream();
    }
}
