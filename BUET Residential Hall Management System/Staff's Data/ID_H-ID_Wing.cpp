#include<iostream>
#include<fstream>
#include<cstring>

using namespace std;

int main()
{
    ofstream out("Staff_ID+H_ID+H_Wing.txt");
    for(int i=1;i<=4;i++)
    {
        for(int j=1;j<=4;j++)
        {
            out<<i<<",'"<<j<<"',NULL"<<endl;
        }
    }
    for(int i=6;i<=7;i++)
    {
        for(int j=1;j<=4;j++)
        {
            out<<i<<",'"<<j<<"',NULL"<<endl;
        }
    }
    for(int i=1;i<=2;i++)
    {
         out<<"5,'"<<i<<"','NORTH'"<<endl;
    }
    for(int i=3;i<=4;i++)
    {
         out<<"5,'"<<i<<"','WEST'"<<endl;
    }
}

