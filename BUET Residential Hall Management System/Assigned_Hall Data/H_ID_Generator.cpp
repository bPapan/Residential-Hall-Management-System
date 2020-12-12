#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    //ifstream in("S_ID+E_ID.txt");
    ofstream out("H_ID_Input.txt");
    string line;
    int j=7;
    for(int i=1;i<=169;i++)
    {
        if(j%7==0)
            out<<7<<endl;
        else
            out<<(j%7)<<endl;
        j++;
    }

}


