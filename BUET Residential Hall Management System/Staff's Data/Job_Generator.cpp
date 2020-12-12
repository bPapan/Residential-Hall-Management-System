#include<fstream>
#include<cstdlib>
#include<cstdio>

using namespace std;

int main()
{
    ofstream out("Job_Input.txt");
    for(int i=1;i<=7;i++)
    {
        out<<"'Cook'"<<endl;
    }
    for(int i=8;i<=14;i++)
    {
        out<<"'Sweeper'"<<endl;
    }
    for(int i=15;i<=21;i++)
    {
        out<<"'Office Staff'"<<endl;
    }
    for(int i=22;i<=25;i++)
    {
        out<<"'Canteen Staff'"<<endl;
    }
    for(int i=26;i<=28;i++)
    {
        out<<"'Cook'"<<endl;
    }
}
