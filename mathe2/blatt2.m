clear all
close all

syms x


A = 10;  % Meter Ertrinkender Ufer
B = 50;  % Meter Streche entlang des Ufers
C = 5;   % Meter David Haselnuss vom Ufer
D = 2;   % Meter pro Sekunde (Laufgeschwindigkeit)
E = 1;   % Meter pro Sekunde (Schwimmgeschwindigkeit)

t(x) = sqrt(A.^2+x.^2)/E+sqrt(C.^2+(B-x).^2)/D

f(x) = diff(t(x), x)
df(x) = diff(t(x), x, x)


%%
TOL   = 1.e-08;
ItMax = 20;
it    = 0;
% Startwert
x = 0;
fprintf("%d-Iterations: x = %.2f, |f(x)| = %.2e\n",it,x,abs(f(x)));

while abs(f(x))>TOL
    it = it+1;
    % Newton-Iteration
    x = x - f(x)/df(x);
    % Ausgabe der Näherungswerte
    fprintf("%d-Iterations: x = %.2f, |f(x)| = %.2e\n",it,x,abs(f(x)));
    % Abbruchkriterium für den Fall, dass das Verfahren nicht konvergiert
    if it>ItMax
        fprintf("never ending story\n");
        break;
    end
end
