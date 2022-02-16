#include <stdio.h>

int main(int argc, char * argv[]){
	char *cmd[100];
	cmd[0]=argv[1];
	printf("%c",cmd[0]);
	int i;
	for(i=0;i<argc;i++){
		printf("argv[%d]:%s\n",i,argv[i]);
	}
	printf("%c\n",*argv[1]);
	exit(0);
}
