#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ofstream out("St_Type.txt");
    //string line1,line2;
    /*for(int i=1;i<=169;i++)
    {
        if(j%7==0)
            out<<7<<endl;
        else
            out<<(j%7)<<endl;
        j++;
    }*/
    /*while(getline(in1,line1)&&getline(in2,line2))
    {
        out<<line1<<","<<line2<<endl;
    }*/
    for(int i=1;i<=169;i++)
    {
        out<<"'Resident'"<<endl;
    }
}

