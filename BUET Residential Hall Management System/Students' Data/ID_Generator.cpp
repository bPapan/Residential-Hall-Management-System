#include<iostream>
#include<fstream>
#include<cstdlib>

using namespace std;

int main()
{
    /*ifstream in1("Sponsors' ID+Name.txt");
    ifstream in2("Sponsors' Manager+Contact.txt");
    ofstream out("Sponsors' Data.txt");
    int cnt1,cnt2,i=0;
    string line,line2,line3;
    while(getline(in1,line) && getline(in2,line2))
    {
        i++;
        out<<"INSERT INTO SPONSORS"<<endl<<"VALUES "<<"("<<i<<","<<line<<line2<<")"<<";"<<endl;
    }*/
    ofstream out1("Students_ID.txt");
    int A[12] = {1,2,4,5,6,10,12,16,15,18,8,17};
    for(int i=2012;i<=2016;i++)
    {
        for(int j=0;j<12;j++)
        {
            if(A[j]<10)
            {
                out1<<"'S"<<i<<"0"<<A[j]<<"0"<<(rand()%21)+10<<"'"<<endl;
                out1<<"'S"<<i<<"0"<<A[j]<<"0"<<(rand()%21)+10<<"'"<<endl;
                out1<<"'S"<<i<<"0"<<A[j]<<"0"<<(rand()%21)+10<<"'"<<endl;
            }
            if(A[j]>=10)
            {
                out1<<"'S"<<i<<A[j]<<"0"<<(rand()%21)+10<<"'"<<endl;
                out1<<"'S"<<i<<A[j]<<"0"<<(rand()%21)+10<<"'"<<endl;
                out1<<"'S"<<i<<A[j]<<"0"<<(rand()%21)+10<<"'"<<endl;
            }
        }
    }
}

