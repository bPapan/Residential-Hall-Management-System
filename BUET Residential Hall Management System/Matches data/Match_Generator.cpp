#include<fstream>
#include<iostream>
#include<cstring>

using namespace std;

int main()
{
    ifstream in1("Match.txt");
    ifstream in2("Dates.txt");
    ofstream out("Match_Input.txt");
    string line1,line2;
    while(getline(in1,line1) && getline(in2,line2))
    {
        out<<"INSERT INTO MATCHES"<<endl<<"VALUES("<<line1<<",TO_DATE('"<<line2<<"','DD/MM/YYYY'));"<<endl;
    }

}



