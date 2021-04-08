package com.example.p4stuinfomanageforhall;

public class ModelStudent {

    String StudentID;
    String Full_Name;
    String Address;

    ModelStudent(){

    }
    ModelStudent(String studentID, String full_Name, String address){
        this.StudentID=studentID;
        this.Full_Name=full_Name;
        this.Address=address;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
