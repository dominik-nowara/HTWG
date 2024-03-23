# Aufgabe 4

## Question 1
**Run process-run.py with the following flags: -l 5:100,5:100. What should the CPU utilization be (e.g., the percent of time the CPU is in use?) Why do you know this? Use the -c and -p flags to see if you were right.**\
Die CPU wird dauerhaft zu 100% belastet, da keine I/O Sachen aufgerufen werden und deswegen der zweite Prozess auf die Vollendung des 1. warten muss.

## Question 2
**Now run with these flags: ./process-run.py -l 4:100,1:0. These flags specify one process with 4 instructions (all to use the CPU), and one that simply issues an I/O and waits for it to be done. How long does it take to complete both processes? Use -c and -p to find out if you were right.**\
Der erste Prozess benötigt 4 ticks und der zweite 5 (normale länge einer I/O operation.) Zusätzlich wird noch ein "ending tick" ausgeführt um zu übermitteln, dass die I/O Operation fertig ist.

## Question 3
**Switch the order of the processes: -l 1:0,4:100. What happens now? Does switching the order matter? Why? (As always, use -c and -p to see if you were right)**\
Beim austauschen der beiden Prozesse wird es in 7 Ticks bereits fertiggestellt. Während der erste Prozess gestartet wird und die I/O Operation durchführt kann in der Zwischenzeit der zweite Prozess seine 4 ticks durchführen. Der 7. tick ist wieder der ending tick.

## Question 4
**We’ll now explore some of the other flags. One important flag is -S, which determines how the system reacts when a process issues an I/O. With the flag set to SWITCH ON END, the system will NOT switch to another process while one is doing I/O, instead waiting until the process is completely finished. What happens when you run the following two processes (-l 1:0,4:100 -c -S SWITCH ON END), one doing I/O and the other doing CPU work?**\
Der 1. Prozess führt den I/O Befehl aus bis er fertig ist, währen der 2. weiterhin auf READY bleibt und erst nachdem der 1. Prozess fertig mit der Operation ist wird dieser gestartet und durchgeführt. Das führt dazu, dass es wie in Aufgabe 2 insgesamt 11 ticks benötigt.

## Question 5
**Now, run the same processes, but with the switching behavior set to switch to another process whenever one is WAITING for I/O (-l 1:0,4:100 -c -S SWITCH ON IO). What happens now? Use -c and -p to confirm that you are right.**\
Es wird exact das Ergebnis aus 3 rausgekommen, da SWITCH_ON_IO die Standard einstellung ist die benutzt wird.

## Question 6
**One other important behavior is what to do when an I/O completes. With -I IO RUN LATER, when an I/O completes, the process that issued it is not necessarily run right away; rather, whatever was running at the time keeps running. What happens when you run this combination of processes? (./process-run.py -l 3:0,5:100,5:100,5:100 -S SWITCH ON IO -c -p -I IO RUN LATER) Are system resources being effectively utilized?**\
Das ausführen des Prgramms benötigt insgesamt 31 Ticks. Hierbei wird der 2. Prozess gestartet, während der 1. Prozess den ersten I/O Befehl ausführt. Nach dem Abschluss des 2. Prozesses werden Prozess 3 und 4 werden nacheinander ausgeführt. Prozess 1 führt die restlichen I/O Befehle dann nach der Beendigung von 4 aus.
Das ist nicht sehr effizient, da es schlauer wäre die Prozesse 3 und 4 während Prozess 1 mit I/O Befeheln beschäftigt ist, auszuführen.

## Question 7
**Now run the same processes, but with -I IO RUN IMMEDIATE set, which immediately runs the process that issued the I/O. How does this behavior differ? Why might running a process that just completed an I/O again be a good idea?**\
Durch die Flag IO_RUN_IMMEDIATE wird nach dem Abschließen eines I/O Befehls direkt der nächste gestartet. Dadurch läuft das Programm nur noch mit 21 ticks (also 10 ticks weniger), da die anderen Prozesse laufen, während bei Prozess 1 sowieso auf das fertigstellen des I/O gewartet werden muss. 


## Question 8
**Now run with some randomly generated processes using flags -s 1 -l 3:50,3:50 or -s 2 -l 3:50,3:50 or -s 3 -l 3:50, 3:50. See if you can predict how the trace will turn out. What happens when you use the flag -I IO RUN IMMEDIATE versus that flag -I IO RUN LATER? What happens when you use the flag -S SWITCH ON IO versus -S SWITCH ON END?**\
Da IO_RUN_IMMEDIATE und IO_RUN_LATER beide die selbe Ausführreihenfolge besitzen ergibt das ungefähr eine gleiche Tick Geschwindigkeit des Programmes. IO_RUN_LATER hat teilweise 1-2 ticks mehr. Dies liegt daran, dass bei beiden flags die CPU Befehle welche 3 Ticks haben auf die fertige Ausführung der I/O Operationen, welche 5 Ticks haben, warten müssen.

SWITCH_ON_IO ist immer schneller, als SWITCH_ON_END.