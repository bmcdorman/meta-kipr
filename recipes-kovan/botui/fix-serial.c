#include "stdio.h"
#include "stdlib.h"
 
int main(int argc, char **argv)
{
    system("systemctl restart kovan-serial.service");
 
        printf("The serial service has been restarted... Carry on.\n");
 
        return 0;
}
