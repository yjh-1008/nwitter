#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
int main(int argc, char *argv[]){
//	if(argc<=0){
//		fprintf(stderr,"Empty Arguments");
//		exit(0);
//	}
	char *commands;
	printf("input commands");
	scanf("%s",commands);
	printf("%s", commands);
	/*printf("%s\n\n",argv[1]);
	int amper=0;
	int size = strlen(argv[1]); //check background or foreground
	printf("%c\n", *argv[2]);	
	if(strcmp(chk[size-1],"&")){
		amper=1;
	}
	printf("%d",amper);
	int pid;*/
	/*if((pid=fork())==0) { //if Child Process
		printf("%s\n", argv[2]);	
		if(strcmp(argv[2],">")){
			printf("redirection\n");

		}else if(strcmp(argv[2],"<")){
			printf("redirection\n");
		}
	
	}else if(pid>0){ // if Parent Process
		printf("%s\n",argv[2]);	

	}else{
		fprintf(stderr,"fork error!");
		exit(0);
	}*/
}
