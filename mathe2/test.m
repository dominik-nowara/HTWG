clear;

%% Cosinus
I = [-pi, pi];
DIM = 100;
x = linspace(I(1), I(2), DIM);
N = 6;

% Definition der Cosinus-Polynomfunktion
Tf_cos = 0;
for k = 0:N
    Tf_cos = Tf_cos + (-1)^k * x.^(2*k) / factorial(2*k);
end

% Berechnung des Integrals
cos_result = integral(@(x) Tf_cos, 0, I(2), 'ArrayValued', true);

% Plotten von cos(x), Taylorpolynom und integriertem Verlauf
figure;
plot(x, cos_result, '-m');
grid on
title('Integrierter Verlauf');
xlabel('x');
ylim([-5, 5]);  % Manuelle Anpassung der y-Limits


%% Sinus
I = [-pi, pi];
DIM = 100;
x = linspace(I(1), I(2), DIM);
N = 18;
Tf_sin = 0;
for k = 0:N
    Tf_sin = Tf_sin + (-1)^k * x.^(2*k+2) / factorial(2*k+2);
end

% Berechnung des Integrals
sin_result = integral(@(x) Tf_sin, 0, I(2), 'ArrayValued', true);

% Plotten von sin(x), Taylorpolynom und integriertem Verlauf
figure;
plot(x, sin_result, '-m');
grid on
title('Integrierter Verlauf');
xlabel('x');
ylim([-5, 5]);  % Manuelle Anpassung der y-Limits