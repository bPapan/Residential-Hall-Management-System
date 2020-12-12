#include<fstream>
#include<iostream>
#include<cstring>
#include<cstdlib>

using namespace std;

int main()
{
    ifstream in1("Staff_ID+H_ID+H_Wing.txt");
    ifstream in2("St_Name+Age.txt");
    ifstream in3("Job_Input.txt");
    ifstream in4("Hire_Date_Input.txt");
    ifstream in5("Staff_Salary.txt");
    ofstream out("Staffs.txt");
    string line1,line2,line3,line4,line5;
    while(getline(in1,line1)&&getline(in2,line2)&&getline(in3,line3)&&getline(in4,line4)&&getline(in5,line5))
    {
        out<<"INSERT INTO STAFFS"<<endl<<"VALUES("<<line1<<","<<line2<<","<<line3<<","<<"TO_DATE('"<<line4<<"', ‘DD/MM/YYYY’)"<<","<<"NULL"<<","<<line5<<");"<<endl;
    }
}
