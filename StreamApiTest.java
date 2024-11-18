package com.streams.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTest {

    //Q. Find the longest string in a list of strings using Java streams:
    public static String longestString(List<String> list){
        Optional<String> longestString = list.stream()
                .max(Comparator.comparingInt(String::length));
        return longestString.get();
    }

    //Q. Find the smallest string in a list of strings using Java streams:
    public static String smallestString(List<String> list){
        Optional<String> longestString = list.stream()
                .min(Comparator.comparingInt(String::length));
        return longestString.get();
    }

    //Q. Calculate the average age of a list of Person objects using Java streams:
    public static double averageAge(){
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );

        double avg = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
        return avg;

    }

    static class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    //Q. Check if a list of integers contains a prime number using Java streams:
    public static boolean checkPrimeNum(List<Integer> list){
        boolean anyMatch = list.stream().anyMatch(integer -> isPrime(integer));
        return anyMatch;
    }

    public static boolean isPrime(int num ){
        if(num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++){
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Q. Merge two sorted lists into a single sorted list using Java streams:
    public static List<Integer> mergeTwoSorted(List<Integer> l1,List<Integer> l2 ){
        return Stream.concat(l1.stream(),l2.stream())
                .sorted().collect(Collectors.toList());
    }

    // Q. Find the intersection of two lists using Java streams:
    public static List<Integer> intersection(List<Integer> l1,List<Integer> l2 ){
        return l1.stream()
                .filter(l2::contains)
                .collect(Collectors.toList());
    }

    //Q. Remove duplicates from a list while preserving the order using Java streams:
    public static List<Integer> intersection(List<Integer> l){
        return l.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    //Q. Given a list of transactions, find the sum of transaction amounts for each day using Java streams:
    static class Transaction{
        Date date;
        int amount;

        public Transaction(Date date, int amount) {
            this.date = date;
            this.amount = amount;
        }

        public Date getDate() {
            return date;
        }

        public int getAmount() {
            return amount;
        }
    }
    public static Map<Date,Integer> sumOfTransactionAmountsByDayWise(List<Transaction> transactionList){
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::getDate,Collectors.summingInt(Transaction::getAmount)));
    }
    //Q. Find the kth smallest element in an array using Java streams:

    public static Integer kthSmallestElement(List<Integer> list, int k){
        return list.stream()
                .distinct()
                .sorted()
                .skip(k-1)
                .findFirst().orElse(-1);
    }

    //Q. Find the kth max element in an array using Java streams:

    public static Integer kthMaxElement(List<Integer> list, int k){
        return list.stream()
                .distinct()
                .sorted((a,b)->b-a)
                .skip(k-1)
                .findFirst().orElse(-1);
    }
    //Q. Given a list of strings, find the frequency of each word using Java streams:
    public static Map<String,Long> frequencyOFEachWord(List<String> list){
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

    //Q. Implement a method to partition a list into two groups based on a predicate using Java streams:
    public static Map<Boolean,List<String>> partitionList(List<String> list){
        return list.stream()
                .collect(Collectors.partitioningBy(w->w.startsWith("b"),Collectors.toList()));
    }



}

