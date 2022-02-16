#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <signal.h>
#define READ 0
#define WRITE 1
int main()
{
	while(1){
		dup2(stdin,0);//set stdin stdout
		dup2(stdout,1);	
		int status;
		char commands[1024];
		char commands1[1024];
		int chk=0;
		fgets(commands,sizeof(commands),stdin);
		commands[strlen(commands)-1]='\0';
		strcpy(commands1,commands);	
		char *sArr[100]={NULL,};
		int count=0;
		char *ptr=strtok(commands1," ");
		while(ptr!=NULL)//divid commands
		{
			sArr[count]=ptr;
			count++;
			ptr=strtok(NULL," ");
		}
		int size= strlen(sArr[0]);
		char cmd1[size];
		strcpy(cmd1,sArr[0]);
		int amper=0;
		if(strcmp(commands,"exit")==0||strcmp(commands,"logout")==0){
			signal(SIGINT,SIG_DFL);
			exit(0);
		}
		if(cmd1[size-1]=='&')//check background process
		{
			amper=1;
		}
		int pid;
		if((pid=fork())==0)
		{
			int chk=1;		
			int exeidx=2;
			int redirectionIdx1,redirectionIdx2=-1;//search redirection index
			if(strchr(commands,'>')!=NULL)
        		{
				int fd, status;
				for(int i=0;i<count;i++){
					if(strcmp(sArr[i],">")==0){
						redirectionIdx1=i;
						break;
					}	
				}
				fd=open(sArr[redirectionIdx1+1],O_CREAT|O_WRONLY,0777);
				dup2(fd,1);
				close(fd);
				chk=2;
			}
			if(strchr(commands,'<')!=NULL)
			{
				int fd1, status;
                        	for(int i=0;i<count;i++){
					if(strcmp(sArr[i],"<")==0){
                                        	redirectionIdx2=i;
                                        	break;
                                	}
                        	}
                        	fd1=open(sArr[redirectionIdx2+1],O_CREAT|O_WRONLY,0777);
                        	dup2(fd1,0);
                        	close(fd1);
				chk=1;
			}

			if(strchr(commands,'|')!=NULL)
                	{
                        	int pid1;
                        	int filedes[2];
                        	pipe(filedes);
				char *front_commands, *end_commands;//divid front of pipe and end of pipe
				front_commands=strtok(commands,"|");
				end_commands=strtok(NULL,"| ");
                        	if((pid1=fork())==0)
                        	{
                                	close(filedes[READ]);
                                	dup2(filedes[WRITE],1);
                                	close(filedes[1]);
					char *div1, *div2;//ls -al 
					div1=strtok(front_commands," ");
					div2=strtok(NULL," ");
        				execlp(sArr[0],div1,div2,NULL);
					perror("pipe");
                        	}
                        	else if(pid1>0)
                        	{
                                	close(filedes[WRITE]);
                                	dup2(filedes[READ],0);
                                	close(filedes[0]);
                                	execlp(end_commands,end_commands,NULL);
                        	}
                	}else{
				char *div1, *div2,*div3;
                        	div1=strtok(commands," ");
                        	div2=strtok(NULL," ");
				if(chk==0){
					execlp(sArr[0],sArr[0],sArr[1],NULL);
				}
	
				if(chk==1)
				{
					execlp(sArr[0],sArr[0],sArr[2],NULL);
				}

				if(chk==2){
					execlp(sArr[0],sArr[0],sArr[1],NULL);
				}
			}	
		}
		else if(pid>0)
		{
			if(amper==0){
				wait(&status);
			}
		}
	}
	return 0;
}	
