# Aufgabe 5 (Simulation)

## Question 1
**Run ./fork.py -s 10 and see which actions are taken. Can you predict what the process tree looks like at each step? Use the -c flag to check your answers. Try some different random seeds (-s) or add more actions (-a) to get the hang of it.**
```
                           Process Tree:
                               a

Action: a forks b              a
                               └── b

Action: a forks c              a
                               ├── b
                               └── c
Action: c EXITS                a
                               └── b

Action: a forks d              a
                               ├── b
                               └── d
Action: a forks e
                               a
                               ├── b
                               ├── d
                               └── e
```

## Question 2
**One control the simulator gives you is the fork percentage, controlled by the -f flag. The higher it is, the more likely the next action is a fork; the lower it is, the more likely the action is an exit. Run the simulator with a large number of actions (e.g., -a 100) and vary the fork percentage from 0.1 to 0.9. What do you think the resulting final process trees will look like as the percentage changes? Check your answer with -c.**\
Eine hohe Fork Prozentrate ergibt einen Baum mit mehr Abstufungen und auch mehr "Nodes" welche sich wiederrum ebenfalls abstufen. Bei einer geringen Rate haben sich nur kleinere Bäume gebildet, da bei diesen die Forks eher exiten, als wirklich neue zu bilden.

## Question 3
**Now, switch the output by using the -t flag (e.g., run ./fork.py -t). Given a set of process trees, can you tell which actions were taken?**
```
                           Process Tree:
                               a

a forks b                      a
                               └── b

b exits                        a

a forks c                      a
                               └── c

c forks d                      a
                               └── c
                                   └── d

c exits                        a
                               └── d
```

## Question 4
**One interesting thing to note is what happens when a child exits; what happens to its children in the process tree? To study this, let’s create a specific example: ./fork.py -A a+b,b+c,c+d,c+e,c-. This example has process ’a’ create ’b’, which in turn creates ’c’, which then creates ’d’ and ’e’. However, then, ’c’ exits. What do you think the process tree should like after the exit? What if you use the -R flag? Learn more about what happens to orphaned processes on your own to add more context.**\
Beim exiten des forks c werden diese an den root process a rangehängt. Wenn man jedoch die -R flag benutzt werden die Prozesse an den Parent von ihrem Parent gehängt. In diesem Falle also b.

## Question 5
**One last flag to explore is the -F flag, which skips intermediate steps and only asks to fill in the final process tree. Run ./fork.py -F and see if you can write down the final tree by looking at the series of actions generated. Use different random seeds to try this a few times.**
```
Action: a forks b
Action: b forks c
Action: a forks d
Action: c forks e
Action: e EXITS

                        Final Process Tree?

                        a
                        ├── b
                        │   └── c
                        └── d
```

## Question 6
**Finally, use both -t and -F together. This shows the final process tree, but then asks you to fill in the actions that took place. By looking at the tree, can you determine the exact actions that took place? In which cases can you tell? In which can’t you tell? Try some different random seeds to delve into this question.**
```
Action?
Action?
Action?
Action?
Action?

                        Final Process Tree:
                               a
                               └── d
```
in einem solchen Fall kann man nicht wirklich sagen, welche Schritte durchgeführt werden mussten, um diesen Baum zu erreichen.

```

a forks b
b forks c
a forks d
d forks e
b forks f

                        Final Process Tree:
                               a
                               ├── b
                               │   ├── c
                               │   └── f
                               └── d
                                   └── e
```
Hier kann man klar sagen, dass es nur forks waren, da wir 5 Actions durchgeführt haben und 6 insgesammte Prozesse haben.

# Aufgabe 5 (Coding)

## Question 1 
**Write a program that calls fork(). Before calling fork(), have the main process access a variable (e.g., x) and set its value to something (e.g., 100). What value is the variable in the child process? What happens to the variable when both the child and parent change the value of x?**\
Beim auführen von *fork()* wird eine Kopie des parent Prozesses erstellt. Hierbei haben beide Prozesse allerdings eigenen Speicher, weswegen beide sich nicht in die quere kommen werden.

## Question 2 
**Write a program that opens a file (with the open() system call) and then calls fork() to create a new process. Can both the child and parent access the file descriptor returned by open()? What happens when they are writing to the file concurrently, i.e., at the same time?**\
Da *open()* vor dem *fork()* ausgeführt wurde, können beide Prozesse darauf zugreifen.\

Beide sind dazu fähig in die Datei zu schreiben, allerdings ist die Reihenfolge wie sie das machen unbestimmt.

## Question 3 
**Write another program using fork(). The child process should print “hello”; the parent process should print “goodbye”. You should try to ensure that the child process always prints first; can you do this without calling wait() in the parent?**\
Ja, es ist möglich. Allerdings ist *wait()* da deutlich besser und zuverlässiger.

## Question 4 
**Write a program that calls fork() and then calls some form of exec() to run the program /bin/ls. See if you can try all of the variants of exec(), including (on Linux) execl(), execle(), execlp(), execv(), execvp(), and execvpe(). Why do you think there are so many variants of the same basic call?**\
1. **execl** und **execv** underscheiden sich durch die Parameter. Führt Befehle mit absoluten Pfaden aus.
2. **execlp** und **execvp** underscheiden sich durch die Parameter. Führt einen Befehl aus der *PATH* Variable aus (Beispielsweise java, falls installiert).
3. **execle** und **execvpe** underscheiden sich durch die Parameter. Diese nehmen als Übergabeparameter strings, welche den Befehl in einem bestimmten Environment ausführen.

## Question 5 
**Now write a program that uses wait() to wait for the child process to finish in the parent. What does wait() return? What happens if you use wait() in the child?**\
**wait() im parent Prozess:** Falls erfolgreich -> Prozess ID des beendeten Child Prozesses. -1, falls es ein error gibt.\

**wait() im child Prozess:** -1, da es keinen Child Prozess im Child Prozess gibt.

## Question 6 
**Write a slight modification of the previous program, this time using waitpid() instead of wait(). When would waitpid() be useful?**\
*waitpid()* wartet darauf, dass ein bestimmter child Prozess beendet wird, anstatt auf alle Child Prozesse zu warten.

## Question 7 
**Write a program that creates a child process, and then in the child closes standard output (STDOUT_FILENO). What happens if the child calls printf() to print some output after closing the descriptor?**\
Wenn wir *STDOUT_FILENO* schließen können wir mit *printf()* nichts mehr auf der Konsole ausgeben lassen. Hierbei wird hier allerdings kein Error ausgegeben oder dergleichen.

## Question 8 
**Write a program that creates two children, and connects the standard output of one to the standard input of the other, using the pipe() system call.**\
Wer erstellen eine Pipe mit read und write. Wir erstellen den 1. child Prozess und bei dem if des parents erstellen wir den 2. child Prozess. 

Das 1. Kind verwendet dup2, damit wir mit stdout an das Ende der Pipe schreiben können. Das 2. Kind verwendet dann dup2, damit stdin das Ende der Pipe auslesen kann. Dadurch werden die beiden Child Prozesse durch die Pipe miteinander verbunden.