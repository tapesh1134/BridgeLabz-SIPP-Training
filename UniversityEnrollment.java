
class Student {

    public int id;
    public String name;
    private double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public double getGPA() {
        return gpa;
    }
}

class Course {

    int id;
    String title;

    Course(int id, String title) {
        this.id = id;
        this.title = title;
    }

}

class Enrollment extends Student {

    public Course cos;
    public String type;

    Enrollment(int id, String name, double gpa, String type, Course cos) {
        super(id, name, gpa);
        this.type = type;
        this.cos = cos;
    }

    public String getDetails() {
        return super.id + " " + super.name + " " + super.getGPA() + " " + cos.title + " " + type;
    }
}

interface graded {

    String getletter(Enrollment e);
}

class letterGrade implements graded {

    Enrollment e;
    String grade;
    String remark;

    letterGrade(Enrollment s, String grade, String remark) {
        this.e = s;
        this.grade = grade;
        this.remark = remark;
    }

    public String getletter(Enrollment s) {
        return e.getDetails() + " " + grade + " " + remark;
    }
}

class Faculty {

    int id;
    String name;

    Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public letterGrade letter(Enrollment s, String grade, String remark) {
        return new letterGrade(s, grade, remark);
    }
}

public class UniversityEnrollment {

    public static void main(String[] args) {
        Course c = new Course(1, "BTECH");
        Enrollment s = new Enrollment(21, "Ram", 5.5, "UG", c);
        Faculty f = new Faculty(2, "Rohan");
        letterGrade ls = f.letter(s, "B", "FAIL");

        System.out.println(ls.getletter(s));
    }
}
