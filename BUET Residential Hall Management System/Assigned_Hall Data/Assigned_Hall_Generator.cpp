#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("Assigned_Student_ID_Input.txt");
    ifstream in2("H_ID_Input.txt");
    ifstream in3("St_Type.txt");
    ofstream out("Assigned_Hall_Data.txt");
    string line1,line2,line3;
    /*for(int i=1;i<=169;i++)
    {
        if(j%7==0)
            out<<7<<endl;
        else
            out<<(j%7)<<endl;
        j++;
    }*/
    while(getline(in1,line1)&&getline(in2,line2)&&getline(in3,line3))
    {
        out<<"INSERT INTO ASSIGNED_HALL"<<endl<<"VALUES("<<line2<<","<<line1<<","<<line3<<");"<<endl;
    }
}




