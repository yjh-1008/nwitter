#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#define READ 0
#define WRITE 1
#define STDIN 0
#define STDOUT 1
#define STDERR 2
#define SIZE 1024 

int main(int argc, char* argv[]) {
    while (1) {

        char str[SIZE];
        int amper = 0;
        int pid1, status;

        printf("[prompt] ");
        fgets(str, sizeof(str), stdin);
        str[strlen(str) - 1] = '\0';
	char str1[SIZE];
	strcpy(str1,str);
        if (strcmp(str, "exit") == 0 || strcmp(str, "logout") == 0) {
            exit(1);
        }

        if (str[strlen(str) - 1] == '&') {
            amper = 1;
            str[strlen(str) - 1] == '\0';
        }
	printf("str is %s\n",str);
        pid1 = fork();

        if (pid1 == 0) {
            char* com[10];
            char* com1[10];
            char* com2[10];
            char* cut[10];
            int i = 0;
            int count = 0;

            char* ptr = strchr(str, ' ');
            char* ctr = strtok(str, " ");
	   printf("in str :%s\n",str);            
            if (ptr != NULL) {
                while (ctr != NULL) {
//		    printf("%s\n",ctr);
                    cut[i]=ctr;
                    ctr = strtok(NULL, " ");
                    printf("%s\n", cut[i]);
                    i++;
                }
                cut[i] = NULL;
                count = i;
            }
		
            if (strchr(str, '|') != NULL) {
                printf("파이프\n");
            }
            else if (strchr(str, '|') == NULL){
               	printf("str is: %s\n",str);
                int i = 1;
                ptr = strchr(str1, ' ');
	        printf("%s\n",ptr);
		if (ptr == NULL) {
      		         cut[0] = str;
                   cut[1] = NULL;
                    execlp(cut[0], cut[0],NULL);
                }
                else{
                   
                   printf("%s\n",cut[1]);
                   execlp(cut[0], cut[0], cut[1], NULL);
                   
                }
                

            }
            exit(1);
        }
        else {
            wait(&status);
        }
    }
}
