#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in("Sponsorship_Data.txt");
    ofstream out("Sponsorship.txt");
    string line;
    while(getline(in,line))
    {
        out<<"INSERT INTO SPONSORSHIP"<<endl<<"VALUES("<<line<<");"<<endl;
    }
}
