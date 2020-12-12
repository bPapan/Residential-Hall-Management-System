#include<iostream>
#include<fstream>
#include<cstring>

using namespace std;

int main()
{
    //ifstream in2("Name.txt");
    ofstream out("Blood_Input.txt");
    int cnt1,cnt2;
    string line,line2;
    /*while(getline(in2,line2))
    {
        out<<"'"<<line2<<"'"<<endl;
    }*/
    string A[22] = {"A+","B+","AB+","B+","A-","AB+","O+","A+","A+","B+","O-","AB+","O+","B-","B+","AB-","A+","A-","B+","O+","A+","A-"};
    for(int i=1;i<=7;i++)
    {
        for(int j=0;j<22;j++)
        {
            out<<"'"<<A[j]<<"'"<<endl;
        }
    }
    for(int j=3;j<=17;j++)
    {
        out<<"'"<<A[j]<<"'"<<endl;
    }
}
