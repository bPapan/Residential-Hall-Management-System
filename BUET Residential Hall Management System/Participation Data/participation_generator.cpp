#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in("S_ID+E_ID.txt");
    ofstream out("participation.txt");
    string line;
    while(getline(in,line))
    {
        out<<"INSERT INTO PARTICIPATION"<<endl<<"VALUES("<<line<<");"<<endl;
    }

}

