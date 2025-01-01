package com.streams.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiTest {
    public static void main(String[] args) {

        //System.out.println(smallestSubarray2(new int[]{2, 4, 6, 10, 2, 1},193));
        //System.out.println(flatMap(Arrays.asList(Arrays.asList(1,2,3,4,5),Arrays.asList(7,8,9,0,6,5),Arrays.asList(7,8,9,0,6,5))));

//        String input = "aa arcles are Awesome";
//
//       Character out =  input.chars().mapToObj(c -> (char) c)
//                .filter(ch -> input.indexOf(ch) == input.lastIndexOf(ch))
//                .findFirst().orElse(null);
//        System.out.println(out);
//        Character out1 = input.chars().mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
//                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(integerLongEntry -> integerLongEntry.getValue() == 1l)
//                .map(entry->entry.getKey())
//                .findFirst()
//                .get();
//        System.out.println(out1);

    }

    public static List<Integer> flatMap(List<List<Integer>> lists){
        return lists.stream().flatMap(f->f.stream()).collect(Collectors.toList());
    }
    //Q.  Given a String, find the first non-repeated character in it using Stream functions?
    public static Character firstNonRepeatedCharacter(String input){
        return input.chars().mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(integerLongEntry -> integerLongEntry.getValue() == 1l)
                .map(entry->entry.getKey())
                .findFirst()
                .get();
    }
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
    //Q. Find all even numbers in a list
    public static List<Integer> even(List<Integer> list){
        return list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    //Q. Find all odd numbers in a list
    public static List<Integer> odd(List<Integer> list){
        return list.stream()
                .filter(n -> n % 2 == 1)
                .collect(Collectors.toList());
    }
    //Q. Partition strings into even and odd lengths
    public static Map<Boolean,List<String>> partitionEvenAndOddLengths(List<String> list){
        return list.stream()
                .collect(Collectors.partitioningBy(s->s.length() % 2 == 0,Collectors.toList()));
    }

    //Q. Join all strings with a delimiter
    public static String joinStringList(List<String> list){
        return list.stream()
                .collect(Collectors.joining(",","[","]"));
    }
    //Q Calculate the sum of a list of integers
    public static Integer sumOdList(List<Integer> list){
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    //Q Calculate the average of a list of integers
    public static Double averageOdList(List<Integer> list){
        return list.stream()
                .mapToInt(Integer::intValue)
                .average().orElse(0.0);
    }


    //Q.  Find the Most Frequent Element in a List
    public static int mostFrequentElement(List<Integer> numbers){
        return numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))// Count occurrences
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())// Find max by value (frequency)
                .map(Map.Entry::getKey)// Get the element with max frequency
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    //Q. Find All Anagrams in a List
    //Problem:
    //Given a list of strings, group all anagrams together.
    // For example, ["eat", "tea", "tan", "ate", "nat", "bat"]
    // should result in groups like [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]].
    public static Map<String,List<String>> findAllAnagramsInList(List<String> list){
        return list.stream()
                .collect(Collectors.groupingBy(word->word.chars()
                        .sorted()
                        .mapToObj(c->String.valueOf((char)c))
                        .collect(Collectors.joining())));
    }

    //Find the Longest Consecutive Sequence in a List
    public static Integer longestConsecutiveSequence(List<Integer> list){
        Set<Integer> numSet = new HashSet<>(list);
        return numSet.stream()
                .filter(n -> !numSet.contains(n - 1)) // Find sequence starts
                .mapToInt(start -> {
                    int length = 1;
                    while (numSet.contains(start + length)) { length++; }
                    return length;
                })
                .max()
                .orElse(0);
    }
    // Find the Smallest Subarray with a Given Sum
    public static Integer smallestSubarray(int[] list, int target ){
        return  IntStream.range(0,list.length)
                .map(start -> IntStream.range(start, list.length)
                        .mapToObj(end->Arrays.copyOfRange(list,start,end+1))
                                .filter(sub->Arrays.stream(sub).sum()>=target)
                                .mapToInt(sub->sub.length)
                                .min().orElse(Integer.MAX_VALUE)
                        ).min().orElse(Integer.MAX_VALUE);
    }

    //{2, 3, 1, 2, 4, 3}
    public static Integer smallestSubarray1(int[] list, int target ){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.length;++i){
            int sum = 0;
            for (int j = i; j < list.length;j++){
                sum +=  list[j];
                if (sum == target) {
                    min = Math.min(min, (j - i + 1));
                    break;
                }if(sum > target){
                    break;
                }
            }

        }
        return  min == Integer.MAX_VALUE ? -1 : min;
    }

    public static Integer smallestSubarray2(int[] list, int target ){

        int min =  IntStream.range(0,list.length)
                .map(i ->
                    IntStream.range(i,list.length)
                            .mapToObj(end->Arrays.copyOfRange(list,i,end+1))
                            .filter(subarray->Arrays.stream(subarray).sum()>target)
                            .mapToInt(m->m.length)
                            .min().orElse(Integer.MAX_VALUE)
//                    int sum = 0;
//                    for (int j = i; j < list.length;j++){
//                        sum +=  list[j];
//                        if (sum == target) {
//                            return j - i + 1;
//                        }if(sum > target){
//                            break ;
//                        }
//                    }
//                    return Integer.MAX_VALUE;
                )
                //.map(i-> i == Integer.MAX_VALUE ? -1 : i)
                .min()
                .orElse(-1);
        return min == Integer.MAX_VALUE ? -1 : min;
    }


}

