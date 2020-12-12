#include<cstdlib>
#include<iostream>
#include<cstdio>
#include<fstream>

using namespace std;

int main()
{
    ifstream in1("ID_Input.txt");
    ifstream in2("Name_Input.txt");
    ifstream in3("Father_Input.txt");
    ifstream in4("Mother_Input.txt");
    ifstream in5("Gender_Input.txt");
    ifstream in6("Religion_Input.txt");
    ifstream in7("Contact_Input.txt");
    ifstream in8("Blood_Input.txt");
    ifstream in9("Address_Input.txt");
    ifstream in10("Status_Input.txt");
    ofstream out("Students.txt");
    string line1,line2,line3,line4,line5,line6,line7,line8,line9,line10;
    while(getline(in1,line1)&&getline(in2,line2)&&getline(in3,line3)&&getline(in4,line4)&&getline(in5,line5)&&getline(in6,line6)&&getline(in7,line7)&&getline(in8,line8)&&getline(in9,line9)&&getline(in10,line10))
    {
        out<<"INSERT INTO STUDENTS"<<endl<<"VALUES("<<line1<<","<<line2<<","<<line3<<","<<line4<<","<<line5<<","<<line6<<","<<line7<<","<<line8<<","<<line9<<","<<line10<<");"<<endl;
    }
}
