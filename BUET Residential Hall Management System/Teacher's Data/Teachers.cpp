#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in("Teachers-Input.txt");
    ofstream out("Teachers.txt");
    string line;
    while(getline(in,line))
    {
        out<<"INSERT INTO TEACHERS"<<endl<<"VALUES("<<line<<");"<<endl;
    }

}
