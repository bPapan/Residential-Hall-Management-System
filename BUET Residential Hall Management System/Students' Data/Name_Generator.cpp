#include<iostream>
#include<fstream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in2("Name.txt");
    ofstream out("Name_Input.txt");
    int cnt1,cnt2;
    string line,line2;
    while(getline(in2,line2))
    {
        out<<"'"<<line2<<"'"<<endl;
    }
}
