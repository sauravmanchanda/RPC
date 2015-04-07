#include <sys/types.h>
#include <ifaddrs.h>
#include <netinet/in.h> 
#include <string> 
#include <arpa/inet.h>

using namespace std;
extern string ip() {
    struct ifaddrs * ifAddrStruct=NULL;
    struct ifaddrs * ifa=NULL;
    void * tmpAddrPtr=NULL;      
    getifaddrs(&ifAddrStruct);

    for (ifa = ifAddrStruct; ifa != NULL; ifa = ifa->ifa_next) {
        if (ifa ->ifa_addr->sa_family==AF_INET) { // check it is IP4
            // is a valid IP4 Address
            tmpAddrPtr=&((struct sockaddr_in *)ifa->ifa_addr)->sin_addr;
            char addressBuffer[INET_ADDRSTRLEN];
            inet_ntop(AF_INET, tmpAddrPtr, addressBuffer, INET_ADDRSTRLEN);
            if (string(ifa->ifa_name).compare("eth0")==0)
                return string(addressBuffer);
            //printf("'%s': %s\n", ifa->ifa_name, addressBuffer); 
         }
    }
    if (ifAddrStruct!=NULL) 
        freeifaddrs(ifAddrStruct);//remember to free ifAddrStruct
    return string("");
}