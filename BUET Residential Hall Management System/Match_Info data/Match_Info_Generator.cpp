#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in("Match.txt");
    ofstream out("Match_Info_Input.txt");
    string line;
    while(getline(in,line))
    {
        out<<"INSERT INTO MATCH_INFO"<<endl<<"VALUES("<<line<<");"<<endl;
    }

}


