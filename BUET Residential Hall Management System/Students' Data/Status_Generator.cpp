#include<iostream>
#include<fstream>
#include<cstring>

using namespace std;

int main()
{
    //ifstream in2("Name.txt");
    ofstream out("Status_Input.txt");
    int cnt1,cnt2;
    string line,line2;
    /*while(getline(in2,line2))
    {
        out<<"'"<<line2<<"'"<<endl;
    }*/
    //string A[50] = {"Chittagong","Rajshahi","Chittagong","Khulna","Mymensingh","Comilla","Sylhet","Jessore","Rajshahi","Bogra","Rangpur","Dinajpur","Gazipur","Dhaka","Barisal","Kushtia","Noakhali","Sylhet","Dhaka","Rajshahi","Chittagong","Rangpur","Nilphamari","Feni","Naogaon","Dhaka","Chittagong","Sirajgonj","Pabna","Faridpur","Patuakhali","Brahmanbaria","Chadpur","Chittagong","Mymensingh","Dhaka","Bogra","Rajshahi","Khulna","Jessore","Rangpur","Dinajpur","Faridpur","Netrakona","Sunamgonj","Sylhet","Kishoreganj","Meherpur","Dhaka","Jamalpur"};
    for(int i=1;i<=3;i++)
    {
        out<<"'Running'"<<endl;
    }
    for(int j=4;j<=31;j++)
    {
        out<<"'Alumni'"<<endl;
    }
    for(int j=32;j<=169;j++)
    {
        out<<"'Running'"<<endl;
    }
}


