#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("Student_ID_Input.txt");
    ifstream in2("S_Date_Input.txt");
    ifstream in3("E_Date_Input.txt");
    ofstream out("Assigned_Room Input.txt");
    int j=0;
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
        j++;
        out<<"INSERT INTO ASSIGNED_ROOM"<<endl<<"VALUES("<<j<<","<<line1<<","<<"TO_DATE('"<<line2<<"','DD/MM/YYYY'),"<<line3<<");"<<endl;
    }
}
