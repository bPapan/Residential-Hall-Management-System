#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("E_ID+H_ID.txt");
    ifstream in2("S_Date.txt");
    ifstream in3("E_Date.txt");
    ofstream out("Event_Info.txt");
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
        out<<"INSERT INTO EVENT_INFO"<<endl<<"VALUES("<<line1<<","<<"TO_DATE('"<<line2<<"','DD/MM/YYYY'),"<<"TO_DATE('"<<line3<<"','DD/MM/YYYY')"<<");"<<endl;
    }
}

