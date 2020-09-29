// --== CS400 File Header Information ==--
// Name: Vedant Sagar
// Email: vsagar@wisc.edu
// Team: KF
// TA: Sid Mohan
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Patient {

  private String name;
  private int facility;
  private int floor;
  private boolean positive;
  private int age;
  private int id;

  Patient(String name, int facility, int floor, boolean positive, int age, int id) {
    this.facility = facility;
    this.floor = floor;
    this.positive = positive;
    this.age = age;
    this.id = id;
    this.name = name;
  }

  Patient(File fileObj) {
    try {
      Scanner input = new Scanner(fileObj);
      while (input.hasNext()) {
        this.name = input.next() + input.next();
        this.facility = input.nextInt();
        this.floor = input.nextInt();
        this.positive = input.nextBoolean();
        this.age = input.nextInt();
        this.id = input.nextInt();
      }
      input.close();
    } catch (FileNotFoundException expt) {
      System.out.println("ERROR: File not found!");
    }
  }

  public int getFacility() {
    return facility;
  }

  public int getFloor() {
    return floor;
  }

  public boolean hasCovid() {
    return positive;
  }

  public int getAge() {
    return age;
  }

  public int getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  /**
   * Prints out all patient information
   */
  public void info() {
    System.out.println(" Name: " + name + " Facility: " + facility + " Floor: " + floor + " Covid: "
        + positive + " Age: " + age + " ID: " + id);
  }

  /**
   * @param capacity capacity of the current hashtable to properly hash values
   * @return hashCode used for key in hashtable
   */
  public int hashCode(int capacity) {
    return (id * 31) % capacity;
  }
}
