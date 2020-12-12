#include<iostream>
#include<fstream>
#include<cstring>
#include<cstdlib>

using namespace std;

int main()
{
    //ifstream in2("Name.txt");
    ofstream out("Contact_Input.txt");
    int cnt1,cnt2;
    string line,line2;
    /*while(getline(in2,line2))
    {
        out<<"'"<<line2<<"'"<<endl;
    }*/
    //string A[22] = {"A+","B+","AB+","B+","A-","AB+","O+","A+","A+","B+","O-","AB+","O+","B-","B+","AB-","A+","A-","B+","O+","A+","A-"};
    for(int i=2;i<=170;i++)
    {
        if((rand()+(rand()%i))<10)
            out<<"'0000"<<(rand()+(rand()%i))<<"'"<<endl;
        if((rand()+(rand()%i))<100)
            out<<"'000"<<(rand()+(rand()%i))<<"'"<<endl;
        if((rand()+(rand()%i))<1000)
            out<<"'00"<<(rand()+(rand()%i))<<"'"<<endl;
        if((rand()+(rand()%i))<10000)
            out<<"'0"<<(rand()+(rand()%i))<<"'"<<endl;
        else
            out<<"'"<<(rand()+(rand()%i))<<"'"<<endl;
    }
}

