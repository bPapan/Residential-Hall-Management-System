#include<iostream>
#include<fstream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("new 2.txt");
    ifstream in2("Name.txt");
    ofstream out("Output.txt");
    int cnt1,cnt2;
    string line,line2;
    while(getline(in1,line))
    {
        out<<"'"<<line<<"'"<<endl;
    }
    /*for(int i=1;i<=169;i++)
    {
        if(i%2==1)
            in1<<"Female"<<endl;
        else
            in1<<"Male"<<endl;
    }*/
}
