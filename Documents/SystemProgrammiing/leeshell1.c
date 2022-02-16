#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#define READ 0
#define WRITE 1
#define SIZE 1024 

int main(int argc, char* argv[]) {
   while (1) {
      dup2(stdin, 0);
      dup2(stdout, 1);

      char str[SIZE];
      char cut[100][SIZE];
      char* ptr;
      int amper = 0;
      int pid1, status;
      int i = 0;
      char com1[100][SIZE];
      char com2[100][SIZE];

      printf("[prompt] ");
      fgets(str, sizeof(str), stdin);
      str[strlen(str) - 1] = '\0';
      

      if (strcmp(str, "exit")==0 || strcmp(str,"logout")==0) {
         exit(1);
      }

      if (str[strlen(str) - 1] ==  '&') {
         amper = 1;
         str[strlen(str)] == '\0';
      }

      pid1 = fork();
      printf("%s\n",str);
      if (pid1 == 0) {

         ptr = strtok(str, " ");
	 printf("ptr is : %s\n",ptr);
         /*while (ptr != NULL) {
	   printf("hello");
            if (strcmp(ptr, ">") == 0) {
               ptr = strtok(NULL, " ");
               int fd;

               fd = open(ptr, O_RDONLY,0777);
               close(stdout);
               dup2(fd,1);
               close(fd);
               ptr = strtok(NULL, " ");
            }
            else if (strcmp(str, "<") != NULL) {
               ptr = strtok(NULL, " ");
               int fd;

               fd = open(ptr, O_RDONLY);
               close(stdin);
               dup2(fd,0);
               close(fd);
               ptr = strtok(NULL, " ");
            }
            strcpy(cut[i], ptr);
	    printf("%s\n", ptr);
            ptr = strtok(NULL, " ");
	    printf("%s\n",ptr);
            i++;
         }*/
	printf("out");
	strcpy(cut[0],"ls");;
        printf("%s\n",cut[0]);
     // while (getchar() != '\n');

        if (strchr(str, "|") != NULL) {
            int pid2;
            int filedes[2];
            int mode = 0;
            int j = 0;

            for (i = 0; i < sizeof(cut)/sizeof(cut[0]); i++) {
               if (strcmp(cut[i], '|') == 0) {
                  mode = 1;
                  j = 0;
                  continue;
               }
               if (mode = 0) {
                  strcpy(com1[j], cut[i]);
                  j++;
               }
               else {
                  strcpy(com2[j], cut[i]);
                  j++;
               }
            }
            pipe(filedes);
            pid2 = fork();
            if (pid2 == 0) {
               close(stdout);
               dup2(filedes[1],1);
               close(filedes[1]);
               close(filedes[0]);
               execvp(com1[0], &com1[0]);
            }
            else {
               close(stdin);
               dup2(filedes[0],0);
               close(filedes[0]);
               close(filedes[1]);
               execvp(com2[0], &com2[0]);
            }
         }else{
         
            printf("last : %s\n",cut[0]);
            execlp(cut[0], cut[0],NULL);
         }
         
      }
      else {
         if (amper == 0) {
            wait(&status);
         }
      }
   }
}
