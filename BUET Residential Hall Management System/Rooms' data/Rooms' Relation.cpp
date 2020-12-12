#include<iostream>
#include<fstream>

using namespace std;

int main()
{
    ofstream out1("Room_ID+Hall_ID.txt");
    ofstream out2("AULA.txt");
    ofstream out3("CHHATTRI.txt");
    ofstream out4("NAZRUL.txt");
    for(int i=1;i<=4;i++)
    {
        for(int j=1;j<=5;j++)
        {
            for(int k=1;k<=12;k++)
            {
                if(k<10)
                    out1<<"'"<<j<<"0"<<k<<"'"<<","<<i<<","<<"4"<<","<<"NULL"<<endl;
                else
                    out1<<"'"<<j<<k<<"'"<<","<<i<<","<<"4"<<","<<"NULL"<<endl;
            }
            for(int k=1;k<=12;k++)
            {
                if(k<10)
                    out1<<"'"<<j<<"00"<<k<<"'"<<","<<i<<","<<"4"<<","<<"NULL"<<endl;
                else
                    out1<<"'"<<j<<"0"<<k<<"'"<<","<<i<<","<<"4"<<","<<"NULL"<<endl;
            }
        }
        if(i!=4)
            out1<<"'Gono',"<<i<<","<<"20,"<< "NULL" <<endl;
        if(i==4)
            out1<<"'Gono',"<<i<<","<<"25,"<< "NULL" <<endl;
    }
    for(int i=1;i<=4;i++)
    {
        for(int j=1;j<=9;j++)
        {
            out2<<"'"<<i<<"0"<<j<<"'"<<",5,"<<"3"<<","<<"'WEST'"<<endl;
        }
        for(int j=10;j<=37;j++)
        {
            out2<<"'"<<i<<j<<"',5,"<<"3"<<","<<"'WEST'"<<endl;
        }
        for(int j=38;j<=61;j++)
        {
            out2<<"'"<<i<<j<<"',5,"<<"3"<<","<<"'NORTH'"<<endl;
        }
    }
    for(int i=1;i<=4;i++)
    {
        for(int j=1;j<=9;j++)
        {
            out3<<"'"<<i<<"0"<<j<<"'"<<",7,"<<"4"<<","<<"NULL"<<endl;
        }
        for(int j=10;j<=36;j++)
        {
            out3<<"'"<<i<<j<<"',7,"<<"4"<<","<<"NULL"<<endl;
        }
    }
    out3<<"'"<<"EXT1"<<"',7,"<<"30"<<","<<"NULL"<<endl;
    out3<<"'"<<"EXT2"<<"',7,"<<"30"<<","<<"NULL"<<endl;
    for(int i=1;i<=4;i++)
    {
        for(int j=1;j<=9;j++)
        {
            out4<<"'"<<i<<"0"<<j<<"',6,"<<"3"<<","<<"NULL"<<endl;
        }
        for(int j=10;j<=24;j++)
        {
            out4<<"'"<<i<<j<<"',6,"<<"3"<<","<<"NULL"<<endl;
        }
    }
    for(int i=1;i<=6;i++)
    {
        out4<<"'E10"<<i<<"',6,"<<"6"<<","<<"NULL"<<endl;
    }
    ifstream in1("Rooms' Data.txt");
    /*ifstream in1("Room_ID.txt");
    ifstream in2("Hall_.txt");
    ifstream in3("Hall_Capacity.txt");*/
    ofstream out("Rooms.txt");
    int cnt1,cnt2,i=0;
    string line,line2,line3;
    while(getline(in1,line))
    {
        out<<"INSERT INTO ROOMS"<<endl<<"VALUES "<<"("<<line<<")"<<";"<<endl;
    }
}


