package models;
import enums.Enums;

public class Student {
    private int id;
    private String Name;
    private int age;
    private double GPA;
    private Enums.MajorType majorType;
    private Enums.Status status;


    public Student(int id, String name, int age, double gpa, Enums.MajorType major, Enums.Status status)
    {
        this.id = id;
        Name = name;
        this.age = age;
        this.GPA = gpa;
        this.majorType = major;
        this.status = status;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public double getGPA() {return GPA;}

    public void setGPA(double GPA) {this.GPA = GPA;}

    public Enums.MajorType getMajorType() {
        return majorType;
    }

    public void setMajorType(Enums.MajorType majorType) {
        this.majorType = majorType;
    }

    public Enums.Status getStatus() {
        return status;
    }

    public void setStatus(Enums.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                ", majorType=" + majorType +
                ", status=" + status +
                '}';
    }
}
