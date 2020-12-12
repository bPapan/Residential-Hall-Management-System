#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in("Team_ID+S_ID+Role.txt");
    ofstream out("Team_Member_Input.txt");
    string line;
    while(getline(in,line))
    {
        out<<"INSERT INTO TEAM_MEMBER"<<endl<<"VALUES("<<line<<");"<<endl;
    }

}

