#include<iostream>
#include<fstream>

using namespace std;

int main()
{
    ifstream in1("Sponsors' ID+Name.txt");
    ifstream in2("Sponsors' Manager+Contact.txt");
    ofstream out("Sponsors' Data.txt");
    int cnt1,cnt2,i=0;
    string line,line2,line3;
    while(getline(in1,line) && getline(in2,line2))
    {
        i++;
        out<<"INSERT INTO SPONSORS"<<endl<<"VALUES "<<"("<<i<<","<<line<<line2<<")"<<";"<<endl;
    }
}



