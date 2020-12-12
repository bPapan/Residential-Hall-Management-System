#include<iostream>
#include<fstream>

using namespace std;

int main()
{
    ifstream in1("Event_ID+Name.txt");
    ofstream out("Events' Data.txt");
    int cnt1,cnt2,i=0;
    string line,line2,line3;
    while(getline(in1,line))
    {
        out<<"INSERT INTO EVENTS"<<endl<<"VALUES "<<"("<<line<<")"<<";"<<endl;
    }
}


