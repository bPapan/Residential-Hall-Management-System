#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("Fee_Name+Amount+Category.txt");
    ofstream out("Fee_Input.txt");
    int j=0;
    string line1,line2,line3;
    /*for(int i=1;i<=169;i++)
    {
        if(j%7==0)
            out<<7<<endl;
        else
            out<<(j%7)<<endl;
        j++;
    }*/
    while(getline(in1,line1))
    {
        j++;
        out<<"INSERT INTO FEES"<<endl<<"VALUES("<<j<<","<<line1<<");"<<endl;
    }
}

