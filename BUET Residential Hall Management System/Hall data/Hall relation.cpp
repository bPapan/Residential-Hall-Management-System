#include<iostream>
#include<fstream>

using namespace std;

int main()
{
    ifstream in1("Hall_Name.txt");
    ifstream in2("Hall_Type.txt");
    ifstream in3("Hall_Capacity.txt");
    ofstream out("Hall.txt");
    int cnt1,cnt2,i=0;
    string line,line2,line3;
    while(getline(in1,line) && getline(in2,line2) && getline(in3,line3))
    {
        i++;
        out<<"INSERT INTO HALL"<<endl<<"VALUES "<<"("<<i<<","<<"'"<<line<<"'"<<","<<"'"<<line2<<"'"<<","<<line3<<")"<<";"<<endl;
    }
}

