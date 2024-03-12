clear all
close all

%% Datensatz - gegebene Punkte eintragen! 
% Eingabe der Punkte im Stil P = [x1 y1]; [x2 y2]; [...]; [xn yn]];
P = [[-1.0 0.92]; [-0.8 0.12]; [-0.6 -0.21]; [-0.4 -0.06]; [0 0.79]; [0.2 1.13]; [0.4 1.34]; [0.6, 1.46]; [0.8 1.49]; [1 1.45];];

% Bestimmen von Schrittweite h und Anzahl der Punkte
h = mean(diff(P(:, 1)));
anzahl = length(P);
fprintf('Wir betrachten %d Datenpunkte mit einer Schrittweite von  %.2e\n\n', anzahl, h)

% Definition der diskreten Ableitungen (gemittelt)
% erste Diskrete Ableitung:
D1 = (diag(ones(anzahl-1, 1), 1) - diag(ones(anzahl-1, 1), -1))/2;
D1 = D1/h;

% zweite diskrete Ableitung:
D2 = -2*diag(ones(anzahl,1))+diag(ones(anzahl-1,1),1)+diag(ones(anzahl-1,1),-1);
D2 = D2/h/h;

% Bestimmen der Ableitungen für unsere Daten:
% erste Ableitung:
d1P = D1 * P(:, 2);
% zweite Ableitung:
d2P = D2 * P(:, 2);

% Plotten des Datensatzes
plot(P(:, 1), P(:, 2), 'ro-')

grid on
hold on
% Plotten der ersten Ableitung
plot(P(:, 1), d1P, 'bo-')
legend('Datensatz', 'Erste Ableitung')
% Plotten der zweiten Ableitung falls nötig
% plot(P(:, 1), d2P, 'mo-')
% legend('Datensatz', 'Erste Ableitung', 'Zweite Ableitung')

%  Bestimmen der Nullstellen vom Datensatz P
IndNS = find(P(1 : end-1, 2) .*P(2 : end, 2) < 0);
xNS = (P(IndNS, 1) + P(IndNS+1, 1)) / 2;
fprintf("Die Funktion hat %d Nullstelle zwischen den Punkten: \n", length(xNS))
% Ausgabe der Nullstellen und der umgebenden Punkte
for i = 1:length(xNS)
    originalIndex = IndNS(i);
    fprintf('x(%d) = %.2f, P(%d, 2) = %.2f und \nx(%d) = %.2f, P(%d, 2) = %.2f \nbei xₙ ≈ %.2f \n\n', ...
            originalIndex, P(IndNS(i), 1), originalIndex, P(IndNS(i), 2), originalIndex+1, P(IndNS(i) + 1, 1), originalIndex+1, P(IndNS(i) + 1, 2), xNS(i));
end
fprintf('\n');

%  Bestimmen der Nullstellen von 1. Ableitung
IndKrit = find(d1P(1:end-1).*d1P(2:end)<0);
xNS1 = (P(IndKrit) + P(IndKrit+1)) / 2;
fprintf("Die Funktion hat %d Kritische Punkte (Nullstellen 1. Ableitung) zwischen den Punkten: \n", length(xNS))
% Ausgabe der Nullstellen und der umgebenden Punkte
for i = 1:length(xNS1)
    originalIndex = IndKrit(i);
    fprintf('x(%d) = %.2f, d1P(%d, 2) = %.2f und \nx(%d) = %.2f, d1P(%d, 2) = %.2f \nbei xₑ ≈ %.2f \n\n', ...
            originalIndex, P(IndKrit(i), 1), originalIndex, d1P(IndKrit(i), 1), originalIndex+1, P(IndKrit(i) + 1, 1), originalIndex+1, d1P(IndKrit(i) + 1, 1), xNS1(i));
end
fprintf('\n');

%  Bestimmen der Nullstellen von 2. Ableitung
IndKrit = find(d2P(1:end-1).*d2P(2:end)<0);
xNS2 = (P(IndKrit) + P(IndKrit+1)) / 2;
fprintf("Die Funktion ggfs. %d Wendepunkte (Nullstellen 2. Ableitung) zwischen den Punkten: \n", length(xNS))
% Ausgabe der Nullstellen und der umgebenden Punkte
for i = 1:length(xNS2)
    originalIndex = IndKrit(i);
    fprintf('x(%d) = %.2f, d2P(%d, 2) = %.2f und \nx(%d) = %.2f, d2P(%d, 2) = %.2f \nbei xw ≈ %.2f \n\n', ...
            originalIndex, P(IndKrit(i), 1), originalIndex, d2P(IndKrit(i), 1), originalIndex+1, P(IndKrit(i) + 1, 1), originalIndex+1, d2P(IndKrit(i) + 1, 1), xNS2(i));
end
fprintf('\n');

%% Ableitung der Funktion

syms x a b y;
f(x) =log(y + a*(b - x)^2) ; % Beispiel Funktion, die muss geändert werden!!
d1f(x) = simplify(diff(f(x), x), 'Steps',30);
d2f(x) = simplify(diff(f(x), x, x), 'Steps',30);

fprintf("1. Ableitung: \n%s\n", d1f(x))
fprintf("2. Ableitung: \n%s\n", d2f(x))

% Die oben bestimmten Werte hier eintragen:
Xn = -0.7; % Nullstelle
Xe = -0.9; % Extemstelle
Xw = -0.9; % Wendepunkt

fx = simplify(solve(f(x), x), 'Steps',30);
d1fx = simplify(solve(d1f, x));
d2fx = simplify(solve(d2f, x), 'Steps',30);

% Funktion und Ableitungen nach x auflösen
fprintf("Welches sind Bedingungen an die Parameter damit f (x) = 0, f ′(x) = 0 und f ′′(x) = 0 erfuellt ist?\n");
fprintf("f(x) = 0   <=> x = %s\n", fx);     % Für x die gewählte Nullstelle Xn einsetzen
fprintf("f(x)′ = 0  <=> x = %s\n", d1fx);   % Für x die gewählte Extremstelle Xe einsetzen
fprintf("f(x)′′ = 0 <=> x = %s\n", d2fx);   % Für x den gewählten Wendepunkt Xw einsetzen

% Falls für ein x mehrere Werte rauskommt, wenn man den linken Punkt als z.B Nullstelle gewaehlt hat
% die Funktion mit dem negativen Vorzeichen nehmen, ansonsten die mit dem Positiven!

fx = fx(2); % Funktion mit negativem Vorzeichen gewaehlt für f(x)
d2fx = d2fx(1); % Funktion mit positiven Vorzeichen gewaehlt für f′′(x)

% Schauen in welcher Ableitung nur eine Variable ist, und nach dieser
% auslösen z.B b in d1f und für x die Nullstelle nehmen, bei 
% df:   f(x)   ist x = Xn
% d1f:  f(x)′  ist x = Xe
% d2f:  f(x)′′ ist x = Xw
b_geloest = solve(d1f(Xe) == 0, b); % nach Beta auflösen

f_mit_beta = subs(fx, b, b_geloest); % Beta in f(x) einsetzen
fprintf("f(x) für b = %s eingesetzt: \nf(x) = %s\n", b_geloest, char(f));
f_geloest = solve(f_mit_beta == Xn, a); % nach Alpha umgestellt und = Extrempunkt

f2 = subs(d2fx, b, b_geloest); % Beta in f(x)′′ einsetzen
f2 = subs(f2, a, f_geloest); % Alpha von f(x) in f(x)′′ einsetzen
y_geloest = solve(f2 == Xw, y); % Nach Gamma umgestellt
fprintf("f(x)′′ nach y umgestellt: y = %.4f\n", double(y_geloest));

f2 = subs(d2fx, b, b_geloest); % Beta in f(x)′′ einsetzen
f2 = subs(f2, y, y_geloest); % Berechnetes Gamma (y) in f(x)′′ einsetzen
a_geloest = solve(f2 == Xw, a); % Nach Alpha (a) umstellen
fprintf("f(x)′′ nach a umgestellt: a = %.4f\n", double(a_geloest));

fprintf("\nb = %.4f, a = %.4f, y = %.4f\n", b_geloest, y_geloest, a_geloest);

% einfach gleich wie oben definieren, irgendwie kann ich hier nichtmehr auf f(x) zugreifen!!
geloest = subs(f(x), [a, b, y], [a_geloest, b_geloest, y_geloest]);
fprintf("Formel mit Werten: f(x): %s\n", char(geloest));



