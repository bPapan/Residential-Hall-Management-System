#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("H_ID+T_ID+Role.txt");
    ifstream in2("SDate.txt");
    ifstream in3("EDate.txt");
    ofstream out("Des_Hall.txt");
    string line1,line2,line3;
    while(getline(in1,line1)&&getline(in2,line2)&&getline(in3,line3))
    {
            out<<"INSERT INTO DESIGNATED_HALL"<<endl<<"VALUES("<<line1<<",(TO_DATE('"<<line2<<"','DD/MM/YYYY'))"<<",(TO_DATE('"<<line3<<"','DD/MM/YYYY)));"<<endl;
    }

}


