#include <string>

// Borland C++ 5.0: bcc32.cpp getlocalip.cpp
// Visual C++ 5.0: cl getlocalip.cpp wsock32.lib
//
// This sample program is hereby placed in the public domain.

#include <iostream>
#include <winsock.h>
# include <string>

using namespace std;

string doit();

extern string ip() {
    WSAData wsaData;
    if (WSAStartup(MAKEWORD(1, 1), &wsaData) != 0) {
        return "";
    }

    string ip = doit();

    WSACleanup();

    return ip;
}

string doit()
{
    char ac[80];
    if (gethostname(ac, sizeof(ac)) == SOCKET_ERROR) {
        cerr << "Error " << WSAGetLastError() <<
                " when getting local host name." << endl;
        return "";
    }
    cout << "Host name is " << ac << "." << endl;

    struct hostent *phe = gethostbyname(ac);
    if (phe == 0) {
        cerr << "Yow! Bad host lookup." << endl;
        return "";
    }

    for (int i = 0; phe->h_addr_list[i] != 0; ++i) {
        struct in_addr addr;
        memcpy(&addr, phe->h_addr_list[i], sizeof(struct in_addr));
        cout << "Address " << i << ": " << inet_ntoa(addr) << endl;
        string s = inet_ntoa(addr);
        return s;
    }

    return "" ;   
}

